package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;

public class Carrier extends Ship{
    public Carrier(Point[] points){
        setLength(5);
        setPoints(points);
    }
}
