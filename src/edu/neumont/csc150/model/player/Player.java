package edu.neumont.csc150.model.player;

public abstract class Player {
    protected String name;
    protected boolean isHuman;

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
}
