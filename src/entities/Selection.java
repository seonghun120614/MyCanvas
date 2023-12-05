package entities;

import java.awt.*;

public class Selection extends Shape{
    private Color fillColor = new Color(0, 0, 255, 100);

    public Selection(int x, int y) {
        super(x, y);
        points = zeroInitPoints(4);
        update();
    }

    @Override
    public void update() {
        points.get(0).set(leftUpperPoint);
        points.get(1).set(rightUpperPoint);
        points.get(2).set(rightLowerPoint);
        points.get(3).set(leftLowerPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(strokeSize);
        g2d.setColor(Color.BLUE);
        g2d.draw(path);
        g2d.setColor(fillColor);
        g2d.fill(path);
    }
}
