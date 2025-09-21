package chess.model;

import chess.model.pieces.Peca;

public class Tabuleiro {

    private Peca[][] pecas;

    public Tabuleiro() {
        this.pecas = new Peca[8][8];
        iniciarPecasPadrao();
    }

    public Peca getPeca(Posicao pos) {
        // Lógica será implementada depois
        return null;
    }

    public void colocarPeca(Peca peca, Posicao pos) {
        // Lógica será implementada depois
    }

    public Peca removerPeca(Posicao pos) {
        // Lógica será implementada depois
        return null;
    }

    private void iniciarPecasPadrao() {
        // A lógica complexa de posicionar as 32 peças virá aqui depois.
        System.out.println("Tabuleiro inicializado (lógica de peças pendente).");
    }
}
