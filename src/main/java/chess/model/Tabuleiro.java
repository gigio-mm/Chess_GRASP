package chess.model;

import chess.model.enums.Cor;
import chess.model.pieces.*;

public class Tabuleiro {

    private Peca[][] pecas;

    public Tabuleiro() {
        this.pecas = new Peca[8][8];
        iniciarPecasPadrao();
    }

    public Peca getPeca(Posicao pos) {
        return pecas[pos.getLinha()][pos.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao pos) {
        pecas[pos.getLinha()][pos.getColuna()] = peca;
    }

    public Peca removerPeca(Posicao pos) {
        Peca removida = pecas[pos.getLinha()][pos.getColuna()];
        pecas[pos.getLinha()][pos.getColuna()] = null;
        return removida;
    }

    private void iniciarPecasPadrao() {
        pecas[0][0] = new Torre(Cor.BRANCO);
        pecas[0][1] = new Cavalo(Cor.BRANCO);
        pecas[0][2] = new Bispo(Cor.BRANCO);
        pecas[0][3] = new Rainha(Cor.BRANCO);
        pecas[0][4] = new Rei(Cor.BRANCO);
        pecas[0][5] = new Bispo(Cor.BRANCO);
        pecas[0][6] = new Cavalo(Cor.BRANCO);
        pecas[0][7] = new Torre(Cor.BRANCO);

        for (int i = 0; i < 8; i++) {
            pecas[1][i] = new Peao(Cor.BRANCO);
        }

        pecas[7][0] = new Torre(Cor.PRETO);
        pecas[7][1] = new Cavalo(Cor.PRETO);
        pecas[7][2] = new Bispo(Cor.PRETO);
        pecas[7][3] = new Rainha(Cor.PRETO);
        pecas[7][4] = new Rei(Cor.PRETO);
        pecas[7][5] = new Bispo(Cor.PRETO);
        pecas[7][6] = new Cavalo(Cor.PRETO);
        pecas[7][7] = new Torre(Cor.PRETO);

        for (int i = 0; i < 8; i++) {
            pecas[7][i] = new Peao(Cor.PRETO);
        }

        System.out.println("Tabuleiro inicializado (lógica de peças pendente).");
    }
}
