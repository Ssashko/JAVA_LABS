package lab2classes;

import java.util.Objects;

public class Circle implements Figure{
    private Point center;
    private Point circlePoint;

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setCirclePoint(Point circlePoint) {
        this.circlePoint = circlePoint;
    }

    public Point getCenter() {
        return center;
    }

    public Point getCirclePoint() {
        return circlePoint;
    }
    public Circle() {
        center = new Point();
        circlePoint = new Point();
    }
    public Circle(Point _c, Point _p) {
        center = _c;
        circlePoint = _p;
    }
    public double getRadius() {
        return Math.sqrt(Math.pow(center.getX()- circlePoint.getX(),2) + Math.pow(center.getY()- circlePoint.getY(),2));
    }

    @Override
    public String call() {
        return "It is a circle";
    }

    @Override
    public double getSquare() {
        return Math.PI*getRadius()*getRadius();
    }

    @Override
    public double getPerimeter() {
        return Math.PI*2*getRadius();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Circle cobj = (Circle)obj;

        return Objects.equals(cobj.center, center) && Objects.equals(cobj.circlePoint, circlePoint);
    }
}
