package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import java.util.List;

public class Torre extends Peca {
    public Torre(Cor cor) {
        super(cor, 5); // Valor da Torre é sempre 5
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Lógica do movimento virá aqui
        return List.of();
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "T" : "t";
    }
}