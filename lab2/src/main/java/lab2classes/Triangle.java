package lab2classes;

import java.util.Objects;

public class Triangle implements Figure {
    private Point[] p;

    public Triangle()
    {
        p = new Point[3];
        p[0] = new Point();
        p[1] = new Point();
        p[2] = new Point();
    }
    public Triangle(Point a, Point b, Point c)
    {
        p = new Point[3];
        p[0] = a;
        p[1] = b;
        p[2] = c;
    }

    public Point[] getPoints() {
        return p;
    }

    public void setPoints(Point[] p) {
        if(p.length != 3)
            throw new IllegalArgumentException("lenght != 3");
        this.p = p;
    }

    public double[] getExtends () {
        double[] _extends = new double[3];
        _extends[0] = Math.sqrt(Math.pow(p[0].getX()-p[1].getX(),2) + Math.pow(p[0].getY()-p[1].getY(),2));
        _extends[1] = Math.sqrt(Math.pow(p[2].getX()-p[1].getX(),2) + Math.pow(p[2].getY()-p[1].getY(),2));
        _extends[2] = Math.sqrt(Math.pow(p[2].getX()-p[0].getX(),2) + Math.pow(p[2].getY()-p[0].getY(),2));
        return _extends;
    }
    @Override
    public String call() {
        return "It is a triangle";
    }

    @Override
    public double getSquare() {
        double p = getPerimeter() / 2;
        double[] _extends = getExtends();
        return Math.sqrt(p*(p-_extends[0])*(p-_extends[1])*(p-_extends[2]));
    }

    @Override
    public double getPerimeter() {
        double[] _extends = getExtends();
        return _extends[0] + _extends[1] + _extends[2];
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Triangle cobj = (Triangle)obj;

        return Objects.equals(cobj.p[0], p[0]) && Objects.equals(cobj.p[1], p[1]) && Objects.equals(cobj.p[2], p[2]);
    }
}
