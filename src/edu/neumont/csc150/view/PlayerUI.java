package edu.neumont.csc150.view;

import edu.neumont.csc150.model.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is entirely gameplay interfaces.
 */

public class PlayerUI {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ConsoleColors colors = new ConsoleColors();

    /**
     * Do you fight or surrender?
     */
    public void attackMenu() {
        System.out.println("It's your turn, what do you do?:\r\n" +
                "\t1. Shoot your shot\r\n" +
                "\t2. Wave your white flag");
    }

    /**
     * placing your ships
     */
    public Point askShipCoords(String type, boolean isVertical) throws IOException {
        System.out.print("Where do you want to put the " + (isVertical ? "topmost":"leftmost") + " segment of your " + type + "? ");
        return parseCoords();
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

    public void displayInvalidPlacement() {
        System.out.println("That placement is invalid. Try again.");
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
                if (coordsString.length() == 2) rowNum = Integer.parseInt((String.valueOf(coordsString.charAt(0))));
                else if (coordsString.length() == 3) rowNum = Integer.parseInt(coordsString.substring(0, coordsString.length() - 1));
                else throw new NumberFormatException();
                if (rowNum > 10 || rowNum < 1) throw new NumberFormatException();
                --rowNum; //1-10 to 0-9

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

    public void displayBoard(int[][] board, boolean isOpponent) { //if isOpponent == true, display 3 the same way as 0
        System.out.println("----a---b---c---d---e---f---g---h---i---j--");
        for (int row = 0; row < board.length; row++) {
            System.out.print((row+1) + (row == 9 ? "|":" |"));
            for (int column = 0; column < board[row].length; column++) {
                int cell = board[row][column];
                switch (cell){
                    case 0: // empty water
                        System.out.print(colors.BLUE + colors.BLUE_BACKGROUND + " " + cell + " ");
                        break;
                    case 1: // miss
                        System.out.print(colors.WHITE + colors.WHITE_BACKGROUND + " " + cell + " ");
                        break;
                    case 2: // hit
                        System.out.print(colors.RED + colors.RED_BACKGROUND + " " + cell + " ");
                        break;
                    case 3: // ship
                        if (isOpponent) System.out.print(colors.BLUE + colors.BLUE_BACKGROUND + " " + cell + " ");
                        else System.out.print(colors.BLACK + colors.BLACK_BACKGROUND + " " + cell + " ");
                        break;
                }
                System.out.print(colors.RESET);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
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

    public void showPlaceShips(String name) {
        System.out.println(name + ", place your ships.");
    }
}
