package edu.neumont.csc150.Human;

public class Player {

    private String name;
    private String piece;
    private boolean isHuman;

    public Player(String name) {
        this.piece = "";
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }
}
