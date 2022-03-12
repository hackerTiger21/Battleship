package edu.neumont.csc150.model.ship;

import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;

public abstract class Ship {
    protected int length;
    protected Point[] points;
    protected ShipType type;

    public int getLength() {
        return length;
    }

    protected void setLength(int length) {
        this.length = length;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public boolean isSunk(){
        boolean allHit = true;
        for (int i = 0; i < points.length; i++) {
            if (!points[i].isHit()) allHit = false;
        }
        return allHit;
    }
}
