package entities;

public class Rectangle extends Shape{

    public Rectangle(int x, int y) {
        super(x, y);

        points = zeroInitPoints(4);
        update();
    }

    public Rectangle(Shape shape) {
        this(shape, true);
    }

    public Rectangle(Shape shape, boolean offset) {
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
