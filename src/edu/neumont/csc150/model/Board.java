package edu.neumont.csc150.model;

public class Board {
    private int[][] board = new int[10][10]; //[row][column]

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
