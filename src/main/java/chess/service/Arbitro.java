package chess.service;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import chess.model.pieces.Peca;
import chess.model.pieces.Rei;

import java.util.List;

public class Arbitro {

    public boolean validarMovimento(Peca peca, Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
        Cor corDoJogador = peca.getCor();

        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.removerPeca(origem);
        tabuleiro.colocarPeca(peca, destino);

        boolean ficouEmXeque = this.verificarXeque(corDoJogador, tabuleiro);

        tabuleiro.colocarPeca(peca, origem);
        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
        }

        return !ficouEmXeque;
    }

    public boolean verificarXeque(Cor corDoRei, Tabuleiro tabuleiro) {
        Posicao posicaoDoRei = encontrarPosicaoDoRei(corDoRei, tabuleiro);
        if (posicaoDoRei == null) {
            return false;
        }

        Cor corInimiga = (corDoRei == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

        // MUDANÇA AQUI: Usa o método especialista do Tabuleiro
        List<Peca> pecasInimigas = tabuleiro.getTodasAsPecasDeCor(corInimiga);

        for (Peca pecaInimiga : pecasInimigas) {
            Posicao posicaoInimiga = tabuleiro.getPosicaoDaPeca(pecaInimiga);
            if (posicaoInimiga != null) {
                List<Posicao> movimentosPossiveis = pecaInimiga.getMovimentosPossiveis(posicaoInimiga, tabuleiro);
                if (movimentosPossiveis.contains(posicaoDoRei)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean verificarXequeMate(Cor cor, Tabuleiro tabuleiro) {
        if (!verificarXeque(cor, tabuleiro)) {
            return false;
        }

        // MUDANÇA AQUI: Usa o método especialista do Tabuleiro
        List<Peca> todasAsPecasAmigas = tabuleiro.getTodasAsPecasDeCor(cor);

        for (Peca peca : todasAsPecasAmigas) {
            Posicao posAtual = tabuleiro.getPosicaoDaPeca(peca);
            if (posAtual == null) continue;

            List<Posicao> movimentosPossiveis = peca.getMovimentosPossiveis(posAtual, tabuleiro);

            for (Posicao destino : movimentosPossiveis) {
                if (validarMovimento(peca, posAtual, destino, tabuleiro)) {
                    return false; // Encontrou uma escapatória
                }
            }
        }

        return true; // Nenhuma escapatória encontrada
    }

    public boolean verificarEmpate(Cor cor, Tabuleiro tabuleiro) {
        // Para ser empate por afogamento, o jogador NÃO PODE estar em xeque.
        if (verificarXeque(cor, tabuleiro)) {
            return false;
        }

        // Agora, verificamos se ele tem algum movimento legal (similar ao xeque-mate)
        List<Peca> todasAsPecasAmigas = tabuleiro.getTodasAsPecasDeCor(cor);

        for (Peca peca : todasAsPecasAmigas) {
            Posicao posAtual = tabuleiro.getPosicaoDaPeca(peca);
            if (posAtual == null) continue;

            List<Posicao> movimentosPossiveis = peca.getMovimentosPossiveis(posAtual, tabuleiro);

            for (Posicao destino : movimentosPossiveis) {
                // Se encontrarmos UM ÚNICO movimento legal, não é empate.
                if (validarMovimento(peca, posAtual, destino, tabuleiro)) {
                    return false;
                }
            }
        }

        // Se o loop terminar e nenhuma jogada legal for encontrada, é empate.
        return true;
    }

    private Posicao encontrarPosicaoDoRei(Cor corDoRei, Tabuleiro tabuleiro) {
        // Este método tem sua única função de encontrar o rei.
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