package entities;

public class Trapezoid extends Shape{

    public Trapezoid(int x, int y) {
        super(x, y);
        points = zeroInitPoints(4);
        update();
    }

    public Trapezoid(Shape shape) {
        this(shape, true);
    }

    public Trapezoid(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(4);
        update();
        connect();
    }

    @Override
    public void update() {
        points.get(0).set(new Point(
                (int) (leftUpperPoint.getX()*0.8 + rightUpperPoint.getX()*0.2),
                leftUpperPoint.getY()
        ));
        points.get(1).set(
                new Point((int) (leftUpperPoint.getX()*0.2 + rightUpperPoint.getX()*0.8),
                        leftUpperPoint.getY())
        );
        points.get(2).set(rightLowerPoint);
        points.get(3).set(leftLowerPoint);
    }
}
