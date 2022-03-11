package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.PlayerLostException;

public class HumanPlayer extends Player{
    public HumanPlayer(String name){
        setName(name);
        setHuman(true);

    }

    @Override
    public void takeTurn() throws PlayerLostException {

        if (hasPlayerLost()) throw new PlayerLostException("I surrender");
    }
}
