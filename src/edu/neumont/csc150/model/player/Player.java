package edu.neumont.csc150.model.player;

public abstract class Player {
    protected String name;
    protected boolean isHuman;
    protected int shipsLeft;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHuman() {
        return isHuman;
    }

    protected void setHuman(boolean human) {
        isHuman = human;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;
    }
}
