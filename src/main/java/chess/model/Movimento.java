package chess.model;

import chess.model.pieces.Peca;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimento {

    private final Peca pecaMovida;
    private final Peca pecaCapturada;
    private final Posicao origem;
    private final Posicao destino;
    private final LocalDateTime dataHora;

    public Movimento(Peca pecaMovida, Posicao origem, Posicao destino, Peca pecaCapturada) {
        this.pecaMovida = pecaMovida;
        this.origem = origem;
        this.destino = destino;
        this.pecaCapturada = pecaCapturada;
        this.dataHora = LocalDateTime.now();
    }

    public Peca getPecaMovida() {
        return pecaMovida;
    }

    public Peca getPecaCapturada() {
        return pecaCapturada;
    }

    public Posicao getOrigem() {
        return origem;
    }

    public Posicao getDestino() {
        return destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String descricao = String.format(
                "%s de %s para %s",
                pecaMovida.getClass().getSimpleName(),
                origem.toString(),
                destino.toString()
        );
        if (pecaCapturada != null) {
            descricao += String.format(" capturando %s", pecaCapturada.getClass().getSimpleName());
        }
        descricao += " em " + dataHora.format(formatter);
        return descricao;
    }
}
