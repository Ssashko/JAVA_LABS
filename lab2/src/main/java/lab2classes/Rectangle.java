package lab2classes;

import java.util.Objects;

public class Rectangle implements Figure{
    private Point leftBottom;
    private Point rightTop;
    public Rectangle()
    {
        leftBottom = new Point();
        rightTop = new Point();
    }
    public Rectangle(Point _leftBottom, Point _rightTop)
    {
        leftBottom = _leftBottom;
        rightTop = _rightTop;
    }

    public void setLeftBottom(Point leftBottom) {
        this.leftBottom = leftBottom;
    }

    public void setRightTop(Point rightTop) {
        this.rightTop = rightTop;
    }

    public Point getLeftBottom() {
        return leftBottom;
    }

    public Point getRightTop() {
        return rightTop;
    }

    public double getWidth()
    {
        Point p = new Point(leftBottom.getX(), rightTop.getY());
        return Math.sqrt(Math.pow(leftBottom.getX() - p.getX(),2) + Math.pow(leftBottom.getY() - p.getY(),2));
    }
    public double getHeight()
    {
        Point p = new Point(leftBottom.getX(), rightTop.getY());
        return Math.sqrt(Math.pow(rightTop.getX() - p.getX(),2) + Math.pow(rightTop.getY() - p.getY(),2));
    }

    @Override
    public String call() {
        return "It is a rectangle";
    }

    @Override
    public double getSquare() {
        return getHeight()*getWidth();
    }

    @Override
    public double getPerimeter() {
        return 2*(getHeight()+getWidth());
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Rectangle cobj = (Rectangle)obj;

        return Objects.equals(cobj.leftBottom, leftBottom) && Objects.equals(cobj.rightTop, rightTop);
    }
}
