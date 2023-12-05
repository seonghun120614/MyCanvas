package entities;

import java.awt.*;

public interface ShapeInterface {
    public void draw(Graphics2D g2d);
    public void select(Graphics2D g2d);
    public void change(Color color);
    public void connect();
    public void update();
}
