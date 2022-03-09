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

    public boolean isSunk(){
        boolean allHit = true;
        for (int i = 0; i < points.length; i++) {
            if (!points[i].isHit()) allHit = false;
        }
        return allHit;
    }
}
