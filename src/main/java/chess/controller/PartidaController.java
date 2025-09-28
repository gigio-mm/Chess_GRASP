package chess.controller;

import chess.model.Movimento;
import chess.model.Partida;
import chess.model.Posicao;
import chess.model.enums.Cor;
import chess.model.enums.StatusPartida;

import java.util.List;
import java.util.Map;

/**
 * A classe Controller é a fachada do sistema.
 * É o único ponto de entrada para qualquer ação externa.
 * Ela delega as responsabilidades para as classes do modelo e de serviço.
 */
public class PartidaController {

    private Partida partida;

    public void iniciarPartida(String nomeJogadorBranco, String nomeJogadorPreto) {
        this.partida = new Partida(nomeJogadorBranco, nomeJogadorPreto);
    }

    public boolean moverPeca(Posicao origem, Posicao destino) {
        if (partida == null) {
            System.err.println("Erro: A partida deve ser iniciada antes de mover uma peça.");
            return false;
        }
        return partida.moverPeca(origem, destino);
    }

    public List<Posicao> getMovimentosPossiveis(Posicao origem) {
        if (partida == null) {
            return List.of(); // Retorna uma lista vazia se a partida não começou
        }
        return partida.getMovimentosPossiveisDe(origem);
    }

    public Map<Cor, Integer> getPontuacao() {
        if (partida == null) {
            return Map.of(); // Retorna um mapa vazio se a partida não começou
        }
        return partida.calcularPontuacao();
    }

    public String exibirVantagem() {
        if (partida == null) {
            return "Partida não iniciada.";
        }

        Map<Cor, Integer> pontuacao = partida.calcularPontuacao();
        int pontosBrancas = pontuacao.getOrDefault(Cor.BRANCO, 0);
        int pontosPretas = pontuacao.getOrDefault(Cor.PRETO, 0);

        if (pontosBrancas > pontosPretas) {
            return "Brancas estão em vantagem com " + pontosBrancas + " pontos contra " + pontosPretas + ".";
        } else if (pontosPretas > pontosBrancas) {
            return "Pretas estão em vantagem com " + pontosPretas + " pontos contra " + pontosBrancas + ".";
        } else {
            return "Jogo empatado em pontos: " + pontosBrancas + " a " + pontosBrancas + ".";
        }
    }

    public List<Movimento> getHistorico() {
        if (partida == null) {
            return List.of();
        }
        return partida.getHistoricoMovimentos();
    }

    public StatusPartida getStatusDaPartida() {
        if (partida == null) {
            return null;
        }
        return partida.getStatus();
    }

    public Partida getPartida() {
        return partida;
    }

    public String getNomeDoJogadorAtual() {
        if (partida == null) {
            return "N/A";
        }
        return partida.getJogadorAtual().getNome();
    }

    // NOVO MÉTODO 2: Para exibir a cor do jogador
    public Cor getCorDoTurnoAtual() {
        if (partida == null) {
            return null;
        }
        return partida.getJogadorAtual().getCor();
    }
}