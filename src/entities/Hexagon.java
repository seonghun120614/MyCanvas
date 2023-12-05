package entities;

public class Hexagon extends Shape{

    public Hexagon(int x, int y) {
        super(x, y);
        points = zeroInitPoints(6);
        update();
    }

    public Hexagon(Shape shape) {
        this(shape, true);
    }

    public Hexagon(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(6);
        update();
        connect();
    }

    public void update() {
        points.get(0).set(new Point(
                (int) (leftUpperPoint.getX()*0.8 + rightUpperPoint.getX()*0.2),
                leftUpperPoint.getY()
        ));
        points.get(1).set(new Point(
                (int) (rightUpperPoint.getX()*0.8 + leftUpperPoint.getX()*0.2),
                rightUpperPoint.getY()
        ));
        points.get(2).set(new Point(
                rightUpperPoint.getX(),
                (rightUpperPoint.getY() + rightLowerPoint.getY())/2
        ));
        points.get(3).set(new Point(
                (int) (rightUpperPoint.getX()*0.8 + leftUpperPoint.getX()*0.2),
                rightLowerPoint.getY()
        ));
        points.get(4).set(new Point(
                (int) (leftUpperPoint.getX()*0.8 + rightUpperPoint.getX()*0.2),
                leftLowerPoint.getY()
        ));
        points.get(5).set(new Point(
                leftUpperPoint.getX(),
                (rightUpperPoint.getY() + rightLowerPoint.getY())/2
        ));
    }
}
