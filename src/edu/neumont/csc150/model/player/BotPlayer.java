package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.PlayerSurrenderedException;
import edu.neumont.csc150.model.Point;

public class BotPlayer extends Player{
    public BotPlayer(String name){
        setName(name);
        setHuman(false);
    }

    @Override
    public Point takeTurn(int[][] enemyBoard) throws PlayerSurrenderedException {
        //empty for now, this is where bot AI goes
        return null;
    }
}
