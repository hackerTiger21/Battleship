package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.PlayerLostException;

public class BotPlayer extends Player{
    public BotPlayer(String name){
        setName(name);
        setHuman(false);
    }

    @Override
    public void takeTurn() throws PlayerLostException {
        //empty for now, this is where bot AI goes
    }
}
