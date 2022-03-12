package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public class Submarine extends Ship{
    public Submarine(){
        setLength(3);
        setType(ShipType.Submarine);
    }
}
