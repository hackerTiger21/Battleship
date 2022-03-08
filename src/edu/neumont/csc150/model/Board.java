package edu.neumont.csc150.model;

public class Board {
    private int[][] board = new int[10][10]; //[row][column]
    //0 - empty water //1 - missed shot //2 - hit shot //3 - undamaged ship

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int takeShot(int row, int column) { //return 0 if target was chosen already, return 1 for hit, return 2 for miss
        int target = board[row][column];
        if (target == 3) {
            board[row][column] = 2; //you hit a ship
            return 1;
        }else if (target == 0) { //you hit empty water
            board[row][column] = 1;
            return 2;
        }
        return 0;
    }
}