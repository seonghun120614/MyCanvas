package entities;

public class Triangle extends Shape{

    public Triangle(int x, int y) {
        super(x, y);
        points = zeroInitPoints(3);
        update();
    }

    public Triangle(Shape shape) {
        this(shape, true);
    }

    public Triangle(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(3);
        update();
        connect();
    }

    @Override
    public void update() {
        points.get(0).set(new Point((leftUpperPoint.getX() + rightUpperPoint.getX())/2,
                leftUpperPoint.getY()));
        points.get(1).set(rightLowerPoint);
        points.get(2).set(leftLowerPoint);
    }
}
