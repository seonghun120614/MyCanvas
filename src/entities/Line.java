package entities;

public class Line extends Shape{

    public Line(int x, int y) {
        super(x, y);
        points = zeroInitPoints(2);
        update();
    }

    public Line(Shape shape) {
        this(shape, true);
    }

    public Line(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(2);
        update();
        connect();
    }

    @Override
    public void update() {
        points.get(0).set(startPoint);
        points.get(1).set(endPoint);
    }
}
