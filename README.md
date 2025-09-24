# Projeto de Xadrez com Padrões GRASP (Chess_GRASP)
---

Este projeto é uma implementação de um back-end para um jogo de xadrez de dois jogadores, desenvolvido como parte da disciplina de Projeto e Arquitetura de Sistemas. O foco principal é a aplicação e demonstração dos *padrões de design GRASP* para criar um sistema robusto, coeso e com baixo acoplamento.

---

## 🚀 Funcionalidades Implementadas

O back-end suporta todas as funcionalidades solicitadas:

- [x] *Movimentar Peças:* O sistema controla e valida o movimento das peças no tabuleiro.
- [x] *Cálculo de Pontuação:* Calcula a vantagem material de cada jogador com base nos valores das peças.
- [x] *Validação de Regras:* Garante a alternância de turnos e que cada peça siga suas regras de movimento.
- [x] *Verificador de Movimentos Possíveis:* Permite consultar todas as jogadas legais para uma peça em uma determinada posição.
- [x] *Histórico de Movimentos:* Armazena e exibe o histórico completo de jogadas da partida.
- [x] *Resolução Automática de Vitória:* O sistema detecta condições de xeque, xeque-mate e empate, encerrando o jogo automaticamente.

---

## 🏛️ Arquitetura e Padrões GRASP

A arquitetura foi projetada seguindo os princípios GRASP para garantir uma clara separação de responsabilidades.

* *Controller (Controlador):* A classe PartidaController atua como a fachada do sistema, recebendo as requisições e delegando-as para a camada de domínio. Ela desacopla a lógica de negócio da interface do usuário (neste caso, o console).

* *Information Expert (Especialista da Informação):* As responsabilidades foram distribuídas para as classes que detêm a informação.
    * Partida: É a especialista no estado do jogo (turno, status, histórico).
    * Tabuleiro: É o especialista na localização das peças.
    * Peca: Cada peça é especialista em suas próprias regras de movimento.

* *Polymorphism (Polimorfismo):* Usado na hierarquia de Peca. A classe Partida interage com o tipo abstrato Peca e chama o método getMovimentosPossiveis(), sem precisar saber o tipo específico da peça, o que torna o sistema extensível e limpo.

* *Pure Fabrication (Fabricação Pura):* As classes PartidaController e Arbitro são fabricações puras. Elas não existem no domínio do xadrez, mas foram criadas para reduzir o acoplamento e aumentar a coesão do sistema, separando as responsabilidades de interface e de validação complexa.

* *High Cohesion (Alta Coesão):* Cada classe tem um propósito único e focado. A Partida gerencia o fluxo, o Arbitro valida as regras, o Tabuleiro gerencia o grid e a Peca define seu movimento.

* *Low Coupling (Baixo Acoplamento):* As dependências são de mão única. O Controller conhece a Partida, mas a Partida não conhece o Controller. Isso torna as classes do núcleo (model, service) reutilizáveis e independentes.

---

## 🛠️ Tecnologias Utilizadas

* *Linguagem:* Java 23
* *Build Tool:* Apache Maven

---

## ⌨️ Comandos da Interface

* mover <origem> <destino> - Realiza uma jogada (ex: mover e2 e4).
* possiveis <posicao> - Mostra os movimentos válidos para uma peça (ex: possiveis g1).
* historico - Exibe o histórico de todas as jogadas.
* ajuda - Mostra este menu de ajuda.
* sair - Encerra o jogo.

---

## 👥 Autores

* Gigio Moura
* Samuel De Francesco 
* Davi Martins
