package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;
import java.util.List;

public class Peao extends Peca {
    public Peao(Cor cor) {
        super(cor, 1); // Valor do Peão é sempre 1
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Lógica do movimento virá aqui
        return List.of();
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "P" : "p";
    }
}