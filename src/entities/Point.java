package entities;

import java.awt.Graphics2D;
import java.awt.Color;


public class Point {
    private int x;
    private int y;
    private int radius = 6;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this(point, true);
    }

    public Point(Point point, boolean offset) {
        if(offset) {
            this.x = point.x + 10;
            this.y = point.y + 10;
        } else {
            this.x = point.x;
            this.y = point.y;
        }
    }

    void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x-radius/2, y-radius/2, radius, radius);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

}
