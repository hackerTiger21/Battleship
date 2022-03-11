package edu.neumont.csc150.model;

public class PlayerLostException extends Exception{
    public PlayerLostException(String errorMessage){
        super(errorMessage);
    }
}
