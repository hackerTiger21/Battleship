package edu.neumont.csc150.model.player;

public class BotPlayer extends Player{
    public BotPlayer(String name){
        setName(name);
        setHuman(false);
        setShipsLeft(5);
    }
}
