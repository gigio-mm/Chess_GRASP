package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import java.util.List;

public class Bispo extends Peca {
    public Bispo(Cor cor) {
        super(cor, 3); // Valor do Bispo é 3
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        return List.of();
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "B" : "b";
    }
}