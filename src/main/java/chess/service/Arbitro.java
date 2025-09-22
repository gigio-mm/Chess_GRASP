package chess.service;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import chess.model.pieces.Peca;
import chess.model.pieces.Rei;

import java.util.List;

public class Arbitro {

    /**
     * Valida um movimento, checando principalmente se o próprio rei não fica em xeque.
     * Usa o padrão "Simular-Testar-Desfazer".
     */
    public boolean validarMovimento(Peca peca, Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
        Cor corDoJogador = peca.getCor();

        // 1. Simula o movimento
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.removerPeca(origem);
        tabuleiro.colocarPeca(peca, destino);

        // 2. Testa a consequência
        boolean ficouEmXeque = this.verificarXeque(corDoJogador, tabuleiro);

        // 3. Desfaz o movimento, restaurando o tabuleiro ao estado original
        tabuleiro.colocarPeca(peca, origem);
        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
        }

        // O movimento só é válido se NÃO deixou o próprio rei em xeque.
        return !ficouEmXeque;
    }

    /**
     * Verifica se o rei de uma determinada cor está sob ataque por alguma peça inimiga.
     */
    public boolean verificarXeque(Cor corDoRei, Tabuleiro tabuleiro) {
        Posicao posicaoDoRei = encontrarPosicaoDoRei(corDoRei, tabuleiro);
        if (posicaoDoRei == null) {
            return false;
        }

        Cor corInimiga = (corDoRei == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicao posAtual = new Posicao(i, j);
                Peca peca = tabuleiro.getPeca(posAtual);

                if (peca != null && peca.getCor() == corInimiga) {
                    List<Posicao> movimentosPossiveis = peca.getMovimentosPossiveis(posAtual, tabuleiro);
                    if (movimentosPossiveis.contains(posicaoDoRei)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Verifica se um jogador está em xeque-mate.
     */
    public boolean verificarXequeMate(Cor cor, Tabuleiro tabuleiro) {
        // 1. Para ser xeque-mate, o jogador DEVE estar em xeque primeiro.
        if (!verificarXeque(cor, tabuleiro)) {
            return false;
        }

        // 2. Agora, verifica se existe ALGUMA jogada legal que o tire do xeque.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicao posAtual = new Posicao(i, j);
                Peca peca = tabuleiro.getPeca(posAtual);

                // Se a peça é da cor do jogador em xeque
                if (peca != null && peca.getCor() == cor) {
                    List<Posicao> movimentosPossiveis = peca.getMovimentosPossiveis(posAtual, tabuleiro);

                    // Testa cada movimento possível
                    for (Posicao destino : movimentosPossiveis) {
                        // Se encontrar UM movimento que seja válido (que tire do xeque)...
                        if (validarMovimento(peca, posAtual, destino, tabuleiro)) {
                            // ...então não é xeque-mate, pois existe uma escapatória.
                            return false;
                        }
                    }
                }
            }
        }

        // Se o loop terminar e nenhuma jogada válida for encontrada, é xeque-mate.
        return true;
    }

    /**
     * Método auxiliar privado para varrer o tabuleiro e encontrar a posição do Rei.
     */
    private Posicao encontrarPosicaoDoRei(Cor corDoRei, Tabuleiro tabuleiro) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicao pos = new Posicao(i, j);
                Peca peca = tabuleiro.getPeca(pos);
                if (peca instanceof Rei && peca.getCor() == corDoRei) {
                    return pos;
                }
            }
        }
        return null;
    }
}