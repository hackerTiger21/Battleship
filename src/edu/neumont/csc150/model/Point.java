package edu.neumont.csc150.model;

public class Point {
    private int row;
    private int column;
    private boolean isHit;

    public Point(int row, int column){
        setRow(row);
        setColumn(column);
        setHit(false);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
