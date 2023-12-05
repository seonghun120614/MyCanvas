package entities;

import java.util.ArrayList;

public class Pencil extends Shape{

    public Pencil(int x, int y) {
        super(x, y);
        points = new ArrayList<>();
    }

    public Pencil(Shape shape) {
        this(shape, true);
    }

    public Pencil(Shape shape, boolean offset) {
        super(shape, offset);
        points = new ArrayList<>();
        for(Point p : shape.points)
            points.add(new Point(p, offset));
        connect();
    }

    @Override
    public void set(int endX, int endY) {
        points.add(new Point(endX, endY));

        leftUpperPoint.set(
                Math.min(leftUpperPoint.getX(), endX),
                Math.min(leftUpperPoint.getY(), endY)
        );
        rightUpperPoint.set(
                Math.max(rightUpperPoint.getX(), endX),
                Math.min(rightUpperPoint.getY(), endY)
        );
        leftLowerPoint.set(
                Math.min(leftLowerPoint.getX(), endX),
                Math.max(leftLowerPoint.getY(), endY)
        );
        rightLowerPoint.set(
                Math.max(rightLowerPoint.getX(), endX),
                Math.max(rightLowerPoint.getY(), endY)
        );
    }

    @Override
    public void connect() {
        path.reset();
        if (points.size() > 1) {
            path.moveTo(points.get(0).getX(), points.get(0).getY());
            for (int i = 1; i < points.size(); i++)
                path.lineTo(points.get(i).getX(), points.get(i).getY());
        }
    }

    @Override
    public void update() {}
}
