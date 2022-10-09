package lab2classes;

import java.util.Objects;

public class Point implements Figure {
    private double x;
    private double y;
    public Point () {
        x = 0;
        y = 0;
    }
    public Point (double _x, double _y) {
        x = _x;
        y = _y;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String call() {
        return "It is a point";
    }

    @Override
    public double getSquare() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }
    @Override
    public boolean equals(Object obj){
        final double EPS = .00001;
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Point pobj = (Point)obj;

        return Math.abs(pobj.x - x) < EPS && Math.abs(pobj.y - y) < EPS;
    }
}
