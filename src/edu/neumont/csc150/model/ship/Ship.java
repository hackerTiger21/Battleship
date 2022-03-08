package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;

public abstract class Ship {
    protected static int length;
    protected Point[] points;

    public static int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
}
