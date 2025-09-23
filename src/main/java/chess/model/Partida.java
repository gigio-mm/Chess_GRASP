package chess.model;

import chess.model.enums.Cor;
import chess.model.enums.StatusPartida;
import chess.model.pieces.Peca;
import chess.service.Arbitro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partida {

    private final Tabuleiro tabuleiro;
    private final Arbitro arbitro;
    private final Jogador jogadorBranco;
    private final Jogador jogadorPreto;
    private Jogador jogadorAtual;
    private StatusPartida status;
    private final List<Movimento> historicoMovimentos;

    public Partida(String nomeJogadorBranco, String nomeJogadorPreto) {
        this.tabuleiro = new Tabuleiro();
        this.arbitro = new Arbitro();
        this.jogadorBranco = new Jogador(nomeJogadorBranco, Cor.BRANCO);
        this.jogadorPreto = new Jogador(nomeJogadorPreto, Cor.PRETO);
        this.jogadorAtual = this.jogadorBranco; // Brancas sempre começam
        this.status = StatusPartida.EM_ANDAMENTO;
        this.historicoMovimentos = new ArrayList<>();
    }

    public boolean moverPeca(Posicao origem, Posicao destino) {
        if (status != StatusPartida.EM_ANDAMENTO && status != StatusPartida.XEQUE) {
            return false;
        }

        Peca pecaMovida = tabuleiro.getPeca(origem);
        if (pecaMovida == null || pecaMovida.getCor() != jogadorAtual.getCor()) {
            return false;
        }

        List<Posicao> movimentosPossiveis = pecaMovida.getMovimentosPossiveis(origem, tabuleiro);
        if (!movimentosPossiveis.contains(destino)) {
            return false;
        }

        if (!arbitro.validarMovimento(pecaMovida, origem, destino, tabuleiro)) {
            return false;
        }

        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.removerPeca(origem);
        tabuleiro.colocarPeca(pecaMovida, destino);
        pecaMovida.notificarQueJaMoveu();

        Movimento movimento = new Movimento(pecaMovida, origem, destino, pecaCapturada);
        this.historicoMovimentos.add(movimento);
        trocarTurno();
        verificarStatusDoJogo();

        return true;
    }

    public List<Posicao> getMovimentosPossiveisDe(Posicao origem) {
        Peca peca = tabuleiro.getPeca(origem);
        if (peca != null) {
            return peca.getMovimentosPossiveis(origem, tabuleiro);
        }
        return List.of(); // Retorna lista vazia se não houver peça
    }

    public Map<Cor, Integer> calcularPontuacao() {
        Map<Cor, Integer> pontuacao = new HashMap<>();
        pontuacao.put(Cor.BRANCO, 0);
        pontuacao.put(Cor.PRETO, 0);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = tabuleiro.getPeca(new Posicao(i, j));
                if (peca != null) {
                    Cor corDaPeca = peca.getCor();
                    int valorDaPeca = peca.getValor();
                    pontuacao.put(corDaPeca, pontuacao.get(corDaPeca) + valorDaPeca);
                }
            }
        }
        return pontuacao;
    }

    public List<Movimento> getHistoricoMovimentos() {
        return this.historicoMovimentos;
    }

    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public Jogador getJogadorAtual() {
        return this.jogadorAtual;
    }

    public StatusPartida getStatus() {
        return this.status;
    }

    private void trocarTurno() {
        this.jogadorAtual = (jogadorAtual == jogadorBranco) ? jogadorPreto : jogadorBranco;
    }

    private void verificarStatusDoJogo() {
        Cor corDoJogadorDaVez = jogadorAtual.getCor();

        if (arbitro.verificarXequeMate(corDoJogadorDaVez, tabuleiro)) {
            this.status = StatusPartida.XEQUE_MATE;
            // O jogador anterior é o vencedor
        } else if (arbitro.verificarEmpate(corDoJogadorDaVez, tabuleiro)) {
            this.status = StatusPartida.EMPATE;
        } else if (arbitro.verificarXeque(corDoJogadorDaVez, tabuleiro)) {
            this.status = StatusPartida.XEQUE;
        } else {
            this.status = StatusPartida.EM_ANDAMENTO;
        }
    }
}