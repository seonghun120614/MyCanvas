package entities;

public class Pentagon extends Shape{

    public Pentagon(int x, int y) {
        super(x, y);
        points = zeroInitPoints(5);
        update();
    }

    public Pentagon(Shape shape) {
        this(shape, true);
    }

    public Pentagon(Shape shape, boolean offset) {
        super(shape, offset);
        points = zeroInitPoints(5);
        update();
        connect();
    }

    public void update() {
        double halfUpperEdge = Math.abs((rightUpperPoint.getX() - leftUpperPoint.getX())/2);

        points.get(0).set(new Point(
                (int) (leftUpperPoint.getX() + halfUpperEdge),
                leftUpperPoint.getY()
        ));
        points.get(1).set(new Point(
                rightUpperPoint.getX(),
                (int)(rightUpperPoint.getY()*0.65 + rightLowerPoint.getY()*0.35)
        ));
        points.get(2).set(new Point(
                (int) (rightLowerPoint.getX()*0.8 + leftLowerPoint.getX()*0.2),
                rightLowerPoint.getY()
        ));
        points.get(3).set(new Point(
                (int) (leftLowerPoint.getX()*0.8 + rightLowerPoint.getX()*0.2),
                leftLowerPoint.getY()
        ));
        points.get(4).set(new Point(
                leftUpperPoint.getX(),
                (int)(leftUpperPoint.getY()*0.65 + leftLowerPoint.getY()*0.35)
        ));
    }
}
