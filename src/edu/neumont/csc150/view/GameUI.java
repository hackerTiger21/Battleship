package edu.neumont.csc150.view;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameUI {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        System.out.println("Which game mode will you choose:\r\n" +
                "\t1. Basic game: (1 shot, next turn)\r\n" +
                "\t2. Artillery: (Shoot until you miss)\r\n" +
                "\t3. Salvo: (Your ships = your shots)");
    }

    /**
     * placing your ships
     */
    public void askShipCoords(ShipType type) {
        System.out.print("Where are you stationing your " + type.name() + "?\r\n");
    }

    /**
     * The game begins, Starting players turn
     */
    public void attackMenu() {
        System.out.println("It your turn, what shall you do?:\r\n" +
                "\t1. Shoot your shot\r\n" +
                "\t2. Wave your white flag");
    }

    public Point askShipCoords(String type, boolean isVertical) throws IOException {
        System.out.print("Where do you want to put the " + (isVertical ? "topmost":"leftmost") + " segment of your " + type + "? ");
        return parseCoords();
    }

    private Point parseCoords() throws IOException {
        System.out.println("Enter the coordinates in number/letter format (e.g. 1a, 6e, 10j, etc.)");
        String coordsString = br.readLine();

        int rowNum = 0;
        char columnChar;
        int columnNum = 0;

        boolean invalidCoords = true;
        do {
            try {
                rowNum = Integer.parseInt(coordsString.substring(0, coordsString.length() - 2));
                if (rowNum > 10 || rowNum < 1) throw new NumberFormatException();

                columnChar = coordsString.charAt(coordsString.length() - 1); //always single character, last index
                if (columnChar >= 'a' && columnChar <= 'j') columnNum = columnChar - 'a';
                else if (columnChar >= 'A' && columnChar <= 'J') columnNum = columnChar - 'A';
                else throw new NumberFormatException();

                invalidCoords = false;
            } catch (NumberFormatException ex){
                System.out.println("Coordinates invalid. Please try again.");
                coordsString = br.readLine(); //get new coords if initial coords are invalid
            }
        } while (invalidCoords);

        return new Point(rowNum,columnNum);
    }

    public boolean askIfShipVertical(String type) throws IOException {
        System.out.println("Do you want to place your " + type + " vertically? (y/n)");
        while (true){
            String input = br.readLine();
            if (input.equals("y") || input.equals("Y")) return true;
            else if (input.equals("n") || input.equals("N")) return false;
            System.out.println("Invalid input, try again. (y/n)");
        }
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

    public void displayUnderConstruction() {
        System.out.println("The bot is currently under construction.\r\n" +
                "(AKA: thee Aliens hacked us & told us to get gud, so we're trying to get it back up and running.)");
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayInvalidPlacement() {
        System.out.println("That placement is invalid. Try again.");
    }
}
