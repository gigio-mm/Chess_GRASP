package chess.model;

import chess.model.enums.Cor;

public class Jogador {
    private final String nome;
    private final Cor cor;

    public Jogador(String nome, Cor cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public Cor getCor() {
        return cor;
    }
}
