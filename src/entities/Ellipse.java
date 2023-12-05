package entities;

public class Ellipse extends Shape {
    public Ellipse(int x, int y) {
        super(x, y);
        points = zeroInitPoints(4);
        update();
        connect();
    }

    public Ellipse(Shape shape) {
        this(shape, true);
    }

    public Ellipse(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(4);
        update();
        connect();
    }

    @Override
    public void update() {
        points.get(0).set(leftUpperPoint);
        points.get(1).set(rightUpperPoint);
        points.get(2).set(rightLowerPoint);
        points.get(3).set(leftLowerPoint);
    }
}
