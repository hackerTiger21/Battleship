package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public class Battleship extends Ship{
    public Battleship(){
        setLength(4);
        setType(ShipType.Battleship);
    }
}
