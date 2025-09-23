package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Torre extends Peca {
    public Torre(Cor cor) {
        super(cor, 5);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();
        int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Cima, Baixo, Esquerda, Direita

        for (int[] d : direcoes) {
            Posicao proxima = new Posicao(posicaoAtual.getLinha() + d[0], posicaoAtual.getColuna() + d[1]);
            while (tabuleiro.isPosicaoValida(proxima)) {
                if (tabuleiro.temPeca(proxima)) {
                    if (tabuleiro.temPecaInimiga(proxima, this.getCor())) {
                        movimentos.add(proxima); // Adiciona a captura
                    }
                    break; // Para o loop pois encontrou uma peça
                }
                movimentos.add(proxima);
                proxima = new Posicao(proxima.getLinha() + d[0], proxima.getColuna() + d[1]);
            }
        }
        return movimentos;
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "T" : "t";
    }
}