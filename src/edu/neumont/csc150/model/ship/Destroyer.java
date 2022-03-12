package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public class Destroyer extends Ship{
    public Destroyer(){
        setLength(2);
        setType(ShipType.Destroyer);
    }
}
