package chess.service;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import chess.model.pieces.Peca;

public class Arbitro {

    public boolean validarMovimento(Peca peca, Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
        // Lógica de validação virá aqui
        return true; // Retorno temporário para não quebrar o código
    }

    public boolean verificarXeque(Cor cor, Tabuleiro tabuleiro) {
        // Lógica de xeque virá aqui
        return false;
    }

    public boolean verificarXequeMate(Cor cor, Tabuleiro tabuleiro) {
        // Lógica de xeque-mate virá aqui
        return false;
    }
}