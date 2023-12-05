package entities;

import java.awt.*;
import java.util.*;

public class Group extends Shape{
    private final ArrayList<Shape> shapes = new ArrayList<>();

    public Group(ArrayList<Shape> shapes) {
        this.shapes.addAll(shapes);
        points = zeroInitPoints(4);
        update();
    }

    public Group(Group group, boolean offset) {
        points = zeroInitPoints(4);

        for(Shape shape: group.shapes) {
            String name = shape.getClass().getName();
            if (name.equals("entities.Rectangle")) {
                this.shapes.add(new Rectangle(shape, offset));
            } else if (name.equals("entities.Line")) {
                this.shapes.add(new Line(shape, offset));
            } else if (name.equals("entities.Triangle")) {
                this.shapes.add(new Triangle(shape, offset));
            } else if (name.equals("entities.Trapezoid")) {
                this.shapes.add(new Trapezoid(shape, offset));
            } else if (name.equals("entities.Pentagon")) {
                this.shapes.add(new Pentagon(shape, offset));
            } else if (name.equals("entities.Hexagon")) {
                this.shapes.add(new Hexagon(shape, offset));
            } else if (name.equals("entities.Ellipse")) {
                this.shapes.add(new Ellipse(shape, offset));
            } else if (name.equals("entities.Pencil")) {
                this.shapes.add(new Pencil(shape, offset));
            }
        }
        update();
    }

    public boolean containsShape(Shape shape) {
        return this.shapes.contains(shape);
    }

    public int getSize() {
        return shapes.size();
    }

    @Override
    public void update() {
        if(shapes.size()>0){
            this.leftUpperPoint.set(shapes.get(0).getLeftUpperPoint());
            this.rightUpperPoint.set(shapes.get(0).getRightUpperPoint());
            this.leftLowerPoint.set(shapes.get(0).getLeftLowerPoint());
            this.rightLowerPoint.set(shapes.get(0).getRightLowerPoint());
        }

        for(Shape shape: shapes){
            if(shape.leftUpperPoint.getX() < this.leftUpperPoint.getX())
                this.leftUpperPoint.setX(shape.leftUpperPoint.getX());
            if(shape.leftUpperPoint.getY() < this.leftUpperPoint.getY())
                this.leftUpperPoint.setY(shape.leftUpperPoint.getY());

            if(shape.rightUpperPoint.getX() > this.rightUpperPoint.getX())
                this.rightUpperPoint.setX(shape.rightUpperPoint.getX());
            if(shape.rightUpperPoint.getY() < this.rightUpperPoint.getY())
                this.rightUpperPoint.setY(shape.rightUpperPoint.getY());

            if(shape.leftLowerPoint.getX() < this.leftLowerPoint.getX())
                this.leftLowerPoint.setX(shape.leftLowerPoint.getX());
            if(shape.leftLowerPoint.getY() > this.leftLowerPoint.getY())
                this.leftLowerPoint.setY(shape.leftLowerPoint.getY());

            if(shape.rightLowerPoint.getX() > this.rightLowerPoint.getX())
                this.rightLowerPoint.setX(shape.rightLowerPoint.getX());
            if(shape.rightLowerPoint.getY() > this.rightLowerPoint.getY())
                this.rightLowerPoint.setY(shape.rightLowerPoint.getY());
        }

        points.get(0).set(leftUpperPoint);
        points.get(1).set(rightUpperPoint);
        points.get(2).set(rightLowerPoint);
        points.get(3).set(leftLowerPoint);
    }

    @Override
    public void change(Color color) {
        for(Shape shape: shapes)
            shape.strokeColor = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        for(Shape shape: shapes) {
            g2d.setStroke(shape.strokeSize);
            g2d.setColor(shape.strokeColor);
            g2d.draw(shape.path);
        }
    }

    public void remove(ArrayList<Shape> sh) {
        shapes.removeAll(sh);
        update();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
