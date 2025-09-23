package chess.model;

import chess.model.enums.Cor;
import chess.model.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private final Peca[][] pecas;

    public Tabuleiro() {
        this.pecas = new Peca[8][8];
        iniciarPecasPadrao();
    }

    public Peca getPeca(Posicao pos) {
        if (!isPosicaoValida(pos)) {
            return null;
        }
        return pecas[pos.getLinha()][pos.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao pos) {
        if (!isPosicaoValida(pos)) {
            return;
        }
        pecas[pos.getLinha()][pos.getColuna()] = peca;
    }

    public Peca removerPeca(Posicao pos) {
        if (!isPosicaoValida(pos)) {
            return null;
        }
        Peca removida = getPeca(pos);
        if (removida != null) {
            pecas[pos.getLinha()][pos.getColuna()] = null;
        }
        return removida;
    }

    private void iniciarPecasPadrao() {
        // Peças Pretas (no topo do tabuleiro, linhas 0 e 1)
        colocarPeca(new Torre(Cor.PRETO), new Posicao(0, 0));
        colocarPeca(new Cavalo(Cor.PRETO), new Posicao(0, 1));
        colocarPeca(new Bispo(Cor.PRETO), new Posicao(0, 2));
        colocarPeca(new Rainha(Cor.PRETO), new Posicao(0, 3));
        colocarPeca(new Rei(Cor.PRETO), new Posicao(0, 4));
        colocarPeca(new Bispo(Cor.PRETO), new Posicao(0, 5));
        colocarPeca(new Cavalo(Cor.PRETO), new Posicao(0, 6));
        colocarPeca(new Torre(Cor.PRETO), new Posicao(0, 7));
        for (int i = 0; i < 8; i++) {
            colocarPeca(new Peao(Cor.PRETO), new Posicao(1, i));
        }

        // Peças Brancas (na base do tabuleiro, linhas 6 e 7)
        for (int i = 0; i < 8; i++) {
            colocarPeca(new Peao(Cor.BRANCO), new Posicao(6, i));
        }
        colocarPeca(new Torre(Cor.BRANCO), new Posicao(7, 0));
        colocarPeca(new Cavalo(Cor.BRANCO), new Posicao(7, 1));
        colocarPeca(new Bispo(Cor.BRANCO), new Posicao(7, 2));
        colocarPeca(new Rainha(Cor.BRANCO), new Posicao(7, 3));
        colocarPeca(new Rei(Cor.BRANCO), new Posicao(7, 4));
        colocarPeca(new Bispo(Cor.BRANCO), new Posicao(7, 5));
        colocarPeca(new Cavalo(Cor.BRANCO), new Posicao(7, 6));
        colocarPeca(new Torre(Cor.BRANCO), new Posicao(7, 7));
    }

    // --- MÉTODOS AUXILIARES ---

    private boolean isPosicaoValida(Posicao pos) {
        int linha = pos.getLinha();
        int coluna = pos.getColuna();
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    public List<Peca> getTodasAsPecasDeCor(Cor cor) {
        List<Peca> pecasDaCor = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = getPeca(new Posicao(i, j));
                if (peca != null && peca.getCor() == cor) {
                    pecasDaCor.add(peca);
                }
            }
        }
        return pecasDaCor;
    }

    public Posicao getPosicaoDaPeca(Peca pecaAlvo) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicao pos = new Posicao(i, j);
                Peca peca = getPeca(pos);
                if (peca != null && peca.equals(pecaAlvo)) {
                    return pos;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  a b c d e f g h\n");
        sb.append(" -----------------\n");
        for (int i = 0; i < 8; i++) {
            sb.append(8 - i).append("|");
            for (int j = 0; j < 8; j++) {
                Peca peca = getPeca(new Posicao(i, j));
                if (peca == null) {
                    sb.append(" -");
                } else {
                    sb.append(" ").append(peca.getRepresentacao());
                }
            }
            sb.append(" |\n");
        }
        sb.append(" -----------------\n");
        return sb.toString();
    }
}