package enums;

import javax.swing.ImageIcon;


public enum DrawAuthority {
    LINE(new ImageIcon("images/draws/line.png"), 10),
    CIRCLE(new ImageIcon("images/draws/ellipse.png"), 11),
    RECTANGLE(new ImageIcon("images/draws/rectangle.png"), 12),
    TRIANGLE(new ImageIcon("images/draws/triangle.png"), 13),
    PENCIL(new ImageIcon("images/draws/pencil.png"), 14),
    TRAPEZOID(new ImageIcon("images/draws/trapezoid.png"), 15),
    PENTAGON(new ImageIcon("images/draws/pentagon.png"), 16),
    HEXAGON(new ImageIcon("images/draws/hexagon.png"), 17);


    private final ImageIcon icon;
    private final int code;

    DrawAuthority(ImageIcon icon, int code) {
        this.icon = icon;
        this.code = code;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getCode() {
        return code;
    }
}
