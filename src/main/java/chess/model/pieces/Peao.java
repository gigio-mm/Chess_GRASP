package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Peao extends Peca {
    public Peao(Cor cor) {
        super(cor, 1);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();
        int linha = posicaoAtual.getLinha();
        int coluna = posicaoAtual.getColuna();

        // Define a direção do movimento com base na cor
        // Peças brancas "sobem" no tabuleiro (índice da linha diminui)
        // Peças pretas "descem" no tabuleiro (índice da linha aumenta)
        int direcao = (getCor() == Cor.BRANCO) ? -1 : 1;

        // Movimento simples para frente
        Posicao frente = new Posicao(linha + direcao, coluna);
        if (tabuleiro.isPosicaoValida(frente) && !tabuleiro.temPeca(frente)) {
            movimentos.add(frente);

            // Movimento duplo (só é possível se o movimento simples também for)
            if (!isJaMoveu()) {
                Posicao frenteDupla = new Posicao(linha + 2 * direcao, coluna);
                if (tabuleiro.isPosicaoValida(frenteDupla) && !tabuleiro.temPeca(frenteDupla)) {
                    movimentos.add(frenteDupla);
                }
            }
        }

        // Capturas nas diagonais
        Posicao diagEsquerda = new Posicao(linha + direcao, coluna - 1);
        if (tabuleiro.isPosicaoValida(diagEsquerda) && tabuleiro.temPecaInimiga(diagEsquerda, getCor())) {
            movimentos.add(diagEsquerda);
        }

        Posicao diagDireita = new Posicao(linha + direcao, coluna + 1);
        if (tabuleiro.isPosicaoValida(diagDireita) && tabuleiro.temPecaInimiga(diagDireita, getCor())) {
            movimentos.add(diagDireita);
        }

        return movimentos;
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "P" : "p";
    }
}