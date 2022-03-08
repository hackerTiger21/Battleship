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

    public ShotType takeShot(int row, int column) {
        int target = board[row][column];
        if (target == 3) {
            board[row][column] = 2; //you hit a ship
            return ShotType.Hit;
        }else if (target == 0) { //you hit empty water
            board[row][column] = 1;
            return ShotType.Miss;
        }
        return ShotType.Invalid; //already hit
    }
}