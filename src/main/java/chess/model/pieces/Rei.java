package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import java.util.List;

public class Rei extends Peca {
    public Rei(Cor cor) {
        super(cor, 0); // Valor do Rei é 0 para o cálculo de pontos
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Lógica do movimento virá aqui
        return List.of();
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "K" : "k";
    }
}