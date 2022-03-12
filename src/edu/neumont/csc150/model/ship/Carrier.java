package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public class Carrier extends Ship{
    public Carrier(){
        setLength(5);
        setType(ShipType.Carrier);
    }
}
