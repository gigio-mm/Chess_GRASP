package chess;

import chess.controller.PartidaController;
import chess.model.Movimento;
import chess.model.Posicao;
import chess.model.enums.StatusPartida;

import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada executável da aplicação.
 * Cria uma interface de linha de comando (CLI) interativa para jogar xadrez
 * e testar todas as funcionalidades do back-end.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaController controller = new PartidaController();

        System.out.println("### BEM-VINDO AO CHESS GRASP - PROJETO DE ARQUITETURA DE SOFTWARE ###");
        controller.iniciarPartida("Jogador 1 (Brancas)", "Jogador 2 (Pretas)");

        // Loop principal do jogo, continua enquanto a partida estiver em andamento ou em xeque
        while (isPartidaEmAndamento(controller)) {
            imprimirEstadoDaPartida(controller);
            System.out.print("Digite seu comando (ou 'ajuda' para ver as opções): ");

            String linhaInput = scanner.nextLine().trim();

            if (linhaInput.equalsIgnoreCase("sair")) {
                break;
            }

            processarComando(linhaInput, controller);
        }

        // Mensagem de fim de jogo
        System.out.println("\n### FIM DE JOGO ###");
        imprimirEstadoDaPartida(controller);
        scanner.close();
    }

    /**
     * Processa o input do usuário e executa a ação correspondente.
     */
    private static void processarComando(String input, PartidaController controller) {
        String[] partes = input.split(" ");
        String comando = partes[0].toLowerCase();

        try {
            switch (comando) {
                case "mover":
                    if (partes.length != 3) throw new IllegalArgumentException("Formato inválido. Use: mover <origem> <destino> (ex: mover e2 e4)");
                    Posicao origem = converterCoordenada(partes[1]);
                    Posicao destino = converterCoordenada(partes[2]);
                    if (!controller.moverPeca(origem, destino)) {
                        System.out.println("\n>>> MOVIMENTO INVÁLIDO! Tente novamente. <<<\n");
                    }
                    break;
                case "possiveis":
                    if (partes.length != 2) throw new IllegalArgumentException("Formato inválido. Use: possiveis <posicao> (ex: possiveis e2)");
                    Posicao pos = converterCoordenada(partes[1]);
                    List<Posicao> movimentos = controller.getMovimentosPossiveis(pos);
                    System.out.println("Movimentos possíveis para a peça em " + partes[1] + ": " + movimentos);
                    break;
                case "historico":
                    System.out.println("\n--- Histórico de Movimentos ---");
                    List<Movimento> historico = controller.getHistorico();
                    if (historico.isEmpty()) {
                        System.out.println("Nenhum movimento foi feito ainda.");
                    } else {
                        historico.forEach(System.out::println);
                    }
                    System.out.println("---------------------------------");
                    break;
                case "ajuda":
                    exibirAjuda();
                    break;
                default:
                    System.out.println("Comando desconhecido. Digite 'ajuda' para ver as opções.");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n>>> ERRO: " + e.getMessage() + " <<<\n");
        }
    }

    /**
     * Imprime o estado atual da partida (tabuleiro, status, vantagem).
     */
    private static void imprimirEstadoDaPartida(PartidaController controller) {
        System.out.println(controller.getPartida().getTabuleiro().toString());
        System.out.println("Status: " + controller.getStatusDaPartida());
        System.out.println(controller.exibirVantagem());
    }

    /**
     * Verifica se a partida deve continuar no loop.
     */
    private static boolean isPartidaEmAndamento(PartidaController controller) {
        StatusPartida status = controller.getStatusDaPartida();
        return status == StatusPartida.EM_ANDAMENTO || status == StatusPartida.XEQUE;
    }

    /**
     * Exibe o menu de ajuda com os comandos disponíveis.
     */
    private static void exibirAjuda() {
        System.out.println("\n--- Comandos Disponíveis ---");
        System.out.println("  mover <origem> <destino>  - Realiza uma jogada (ex: mover e2 e4)");
        System.out.println("  possiveis <posicao>     - Mostra os movimentos válidos para uma peça (ex: possiveis g1)");
        System.out.println("  historico                 - Exibe o histórico de todas as jogadas");
        System.out.println("  ajuda                     - Mostra este menu de ajuda");
        System.out.println("  sair                      - Encerra o jogo");
        System.out.println("----------------------------\n");
    }

    /**
     * Converte uma coordenada de xadrez (ex: "e2") para um objeto Posicao (ex: new Posicao(6, 4)).
     */
    private static Posicao converterCoordenada(String coord) {
        if (coord.length() != 2) {
            throw new IllegalArgumentException("Coordenada '" + coord + "' inválida. Use o formato letra+número (ex: a1, h8).");
        }
        char colunaChar = coord.charAt(0);
        char linhaChar = coord.charAt(1);

        if (colunaChar < 'a' || colunaChar > 'h' || linhaChar < '1' || linhaChar > '8') {
            throw new IllegalArgumentException("Coordenada '" + coord + "' fora do tabuleiro.");
        }

        int coluna = colunaChar - 'a'; // 'a' se torna 0, 'b' se torna 1, etc.
        int linha = 8 - Character.getNumericValue(linhaChar); // '1' se torna 7, '8' se torna 0.

        return new Posicao(linha, coluna);
    }
}