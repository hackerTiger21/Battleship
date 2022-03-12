package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public class Cruiser extends Ship{
    public Cruiser(){
        setLength(3);
        setType(ShipType.Cruiser);
    }
}
