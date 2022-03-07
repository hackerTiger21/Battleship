package edu.neumont.csc150.model;

public class Board {
    private int[][] board = new int[10][10]; //[row][column]



    private static void displayBoard(String[][] board) {
        for (int row = 0; row < board.length; row++) {

            System.out.print("|");
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(" " + (board[row][column]) + " |");
            }
            System.out.println();
            System.out.println("----------------------");
        }
    }

    private static void lRow(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[column][row]);
            }
            System.out.println();
        }
    }

    private static void lColumn(int[][] board) {
        for (int column = 0; column < board.length; column++) {
            for (int row = 0; row < board[row].length; row++) {
                System.out.println(board[row][column]);
            }
            System.out.println();
        }
    }

    private static void lEachOne(int[][] board) {
        for (int[] rowOfInts : board) {
            for (int n : rowOfInts) {
                System.out.print(n);
            }
        }
        System.out.println();
    }
}
