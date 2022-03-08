package edu.neumont.csc150.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameUI {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void displayMenu() {
        System.out.println("Prepare for battle: Its BattleShip\r\n" +
                "Are you ready:\r\n" +
                "\t1. Player Vs. Player\r\n" +
                "\t2. Player Vs. the Aliens(Bot)\r\n" +
                "\t3. Wave your White FLag (that means exit)\r\n");
    }

    public void askGameModeMenu() {

        System.out.println("Which game mode will you choose:\r\n" +
                "\t1. Basic game: (1 shot, next turn)\r\n" +
                "\t2. Artillery: (Shoot until you miss)\r\n" +
                "\t3. Salvo: (Your ships = your shots)");
    }

    public void attackMenu() {

        System.out.println("It your turn, what shall you do?:\r\n" +
                "\t1. Shoot your shot\r\n" +
                "\t2. Wave your white flag\r\n");
    }

    public void boatMenu(){
        System.out.println("Where are you stationing your Ships?\r\n");
    }

    public int getUserInputAsInt(int min, int max) throws IOException {
        int input;
        do {
            String line = br.readLine();
            try {
                input = Integer.parseInt(line);
                if (input > max || input < min) {
                    throw new NumberFormatException("out of range");
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Input invalid. " +
                        "You must enter a number between " + min + " and " + max);
            }
        } while (true);
        return input;
    }



    public void displayIOError() {
        System.out.println("We ran into an error in IO operations. Let's start over.");
    }

    public void displayBoard(int[][] board, boolean isOpponent) { //if isOpponent == true, display 3 the same way as 0
        for (int row = 0; row < board.length; row++) {
            System.out.print("|");
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(" " + (board[row][column]) + " |");
            }
            System.out.println();
            System.out.println("----------------------");
        }
    }

    public String askName(boolean isPlayerOne) throws IOException {
        System.out.print("What is the name of " + (isPlayerOne ? "Player 1" : "Player 2") + "? ");
        String input = br.readLine();
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid name");
        }
        return input;
    }
}
