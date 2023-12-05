package entities;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public abstract class Shape implements ShapeInterface{
    ArrayList<Point> points;
    Point startPoint;
    Point endPoint;
    Point leftUpperPoint;
    Point rightUpperPoint;
    Point leftLowerPoint;
    Point rightLowerPoint;
    Path2D path = new Path2D.Double();
    BasicStroke strokeSize = new BasicStroke(2.0f);
    Color strokeColor = Color.BLACK;

    public Shape() {
        this(0, 0);
    }

    public Shape(int x, int y) {
        startPoint = new Point(x, y);
        endPoint = new Point(x, y);
        leftUpperPoint = new Point(x, y);
        rightUpperPoint = new Point(x, y);
        leftLowerPoint = new Point(x, y);
        rightLowerPoint = new Point(x, y);
    }

    public Shape(Shape shape, boolean offset) {
        this(shape);
        startPoint = new Point(shape.startPoint, offset);
        endPoint = new Point(shape.endPoint, offset);
        leftUpperPoint = new Point(shape.leftUpperPoint, offset);
        rightUpperPoint = new Point(shape.rightUpperPoint, offset);
        leftLowerPoint = new Point(shape.leftLowerPoint, offset);
        rightLowerPoint = new Point(shape.rightLowerPoint, offset);
    }

    public Shape(Shape shape) {
        strokeSize = shape.strokeSize;
        strokeColor = shape.strokeColor;
    }

    public void set(int endX, int endY) {
        endPoint = new Point(endX, endY);
        int x = startPoint.getX();
        int y = startPoint.getY();

        leftUpperPoint.set(
                Math.min(x, endX),
                Math.min(y, endY)
        );
        rightUpperPoint.set(
                Math.max(x, endX),
                Math.min(y, endY)
        );
        leftLowerPoint.set(
                Math.min(x, endX),
                Math.max(y, endY)
        );
        rightLowerPoint.set(
                Math.max(x, endX),
                Math.max(y, endY)
        );
    }

    public boolean contains(int x, int y) {
        return path.contains(x, y);
    }

    public boolean in(Rectangle2D rect) {
        return rect.contains(path.getBounds2D());
    }

    @Override
    public void change(Color color) {
        strokeColor = color;
    }

    @Override
    public void select(Graphics2D g2d) {
        for(Point point: points) point.draw(g2d);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(strokeSize);
        g2d.setColor(strokeColor);
        if(this.getClass().getName().equals("entities.Ellipse")) {
            g2d.drawOval(
                    leftUpperPoint.getX(), leftUpperPoint.getY(),
                    rightLowerPoint.getX() - leftUpperPoint.getX(),
                    rightLowerPoint.getY() - leftUpperPoint.getY()
            );
            return;
        }
        g2d.draw(path);
    }

    @Override
    public void connect() {
        path.reset();
        if (points.size() > 1) {
            path.moveTo(points.get(0).getX(), points.get(0).getY());
            for (int i = 1; i < points.size(); i++)
                path.lineTo(points.get(i).getX(), points.get(i).getY());
            path.closePath();
        }
    }

    protected ArrayList<Point> zeroInitPoints(int n) {
        ArrayList<Point> tmp = new ArrayList<>(n);
        for(int i=0; i<n; i++) tmp.add(new Point(0, 0));
        return tmp;
    }

    public Point getLeftUpperPoint() {
        return leftUpperPoint;
    }

    public Point getLeftLowerPoint() {
        return leftLowerPoint;
    }

    public Point getRightUpperPoint() {
        return rightUpperPoint;
    }

    public Point getRightLowerPoint() {
        return rightLowerPoint;
    }
}
