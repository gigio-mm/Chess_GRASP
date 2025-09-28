package chess.model;

public class Posicao {

    private final int linha;
    private final int coluna;

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override
    public String toString() {
        char colunaChar = (char) ('a' + coluna);
        int linhaNum = 8 - linha;
        return String.valueOf(colunaChar) + linhaNum;
    }
}

