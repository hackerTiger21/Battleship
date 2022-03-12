package edu.neumont.csc150.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class handles the UI for the main game program. All gameplay interface is in PlayerUI
 */

public class GameUI {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ConsoleColors colors = new ConsoleColors();
    /**
     *Starting the game
     */
    public void displayMenu() {
        System.out.println("Prepare for battle: Its BattleShip\r\n" +
                "Are you ready:\r\n" +
                "\t1. Capitan Vs. Capitan\r\n" +
                "\t2. Capitan Vs. the Aliens(Bot)(Under Development) \r\n" +
                "\t3. Wave your White FLag (Exit Program)");
    }

    /**
     * Choosing a game mode
     */
    public void askGameModeMenu() {
        System.out.println("Which game mode do you want?\r\n" +
                "\t1. Basic game: (1 shot, next turn)\r\n" +
                "\t2. Artillery: (Shoot until you miss)\r\n" +
                "\t3. Salvo: (Your ships = your shots)");
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



    public String askName(boolean isPlayerOne) throws IOException {
        System.out.print("What is the name of " + (isPlayerOne ? "Player 1" : "Player 2") + "? ");
        String input = br.readLine();
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid name");
        }
        return input;
    }

    public void displayUnderConstruction() {
        System.out.println("This part is under construction.");
    }

    public void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public void switchPlayers() throws IOException {
        System.out.println("Press enter to clear the screen");
        br.readLine();
        clearConsole();
        System.out.println("Pass the device to the other player, press Enter when ready.");
        br.readLine();
        clearConsole();
    }

    public void displayWin(String name) {
        System.out.println(name + "wins!");
    }
}
