package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.model.PlayerLostException;
import edu.neumont.csc150.model.ship.Ship;
import edu.neumont.csc150.view.PlayerUI;

import java.util.ArrayList;

public abstract class Player {
    protected String name;
    protected boolean isHuman;
    protected ArrayList<Ship> ships;
    protected Board playerBoard;
    protected boolean playerLost = false;
    PlayerUI ui = new PlayerUI();

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

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public boolean hasPlayerLost() {
        return playerLost;
    }

    public void setPlayerLost(boolean playerLost) {
        this.playerLost = playerLost;
    }

    public void checkForLoss(){
        boolean allSunk = true;
        for (Ship ship : ships) {
            if (!ship.isSunk()) allSunk = false;
        }
        setPlayerLost(allSunk);
    }

    public abstract void takeTurn() throws PlayerLostException;
}
