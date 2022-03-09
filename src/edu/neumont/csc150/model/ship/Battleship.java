package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;

public class Battleship extends Ship{
    public Battleship(Point[] points){
        setLength(4);
        setPoints(points);
    }
}
