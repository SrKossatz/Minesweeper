package com.kossatzdev.cm.view;

import com.kossatzdev.cm.excecao.ExitException;
import com.kossatzdev.cm.excecao.ExplosionException;
import com.kossatzdev.cm.model.Board;

import java.util.Arrays;
import java.util.Scanner;

public class Console {

    private Board board;
    private Scanner input = new Scanner(System.in);

    public Console(Board board) {
        this.board = board;
        executeGame();
    }

    private void executeGame() {
        try {
            boolean keepGoing = true;
            while (keepGoing) {
                gameLoop();

                System.out.println("Outra partida? Digite (S/n) ");
                String answer = input.nextLine();
                if ("n".equalsIgnoreCase(answer)) {
                    keepGoing = false;
                } else {
                    board.reset();
                }
            }
        } catch (ExitException e) {
            System.out.println("Bye-bye...");
        } finally {
            input.close();
        }
    }

    private void gameLoop() {
        try {
            while (!board.objectiveAchieved()) {
                System.out.println(board);

                int[] coordinates = readCoordinates();
                int row = coordinates[0];
                int col = coordinates[1];

                int action = readAction();

                if (action == 1) {
                    board.openField(row, col);
                } else {
                    board.toggleMarking(row, col);
                }
            }
            System.out.println(board);
            System.out.println("\uD83C\uDF89 Parabéns, você ganhou!!");

        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("\uD83D\uDCA5 Ohhh nãooo. Você perdeu!!!");
        }
    }

    private int[] readCoordinates() {
        while (true) {
            String inputValue = catchInputValue("Digite o valor da (linha,coluna): ");
            inputValue = inputValue.replace(".", ",");

            String[] parts = inputValue.split(",");
            if (parts.length == 2) {
                try {
                    int row = Integer.parseInt(parts[0].trim());
                    int col = Integer.parseInt(parts[1].trim());
                    return new int[]{row, col};
                } catch (NumberFormatException e) {
                    System.out.println("❌ Entrada inválida. Digite números separados por vírgula.");
                }
            } else {
                System.out.println("❌ Formato inválido. Use: linha,coluna (ex: 1,3)");
            }
        }
    }

    private int readAction() {
        while (true) {
            String inputValue = catchInputValue("Digite: 1 - Abrir Campo ou 2 - Marcar Campo: ");
            if ("1".equals(inputValue) || "2".equals(inputValue)) {
                return Integer.parseInt(inputValue);
            } else {
                System.out.println("❌ Opção inválida. Escolha 1 ou 2.");
            }
        }
    }

    private String catchInputValue(String text) {
        System.out.print(text);
        String inputValue = input.nextLine();

        if ("sair".equalsIgnoreCase(inputValue)) {
            throw new ExitException();
        }
        return inputValue;
    }
}
