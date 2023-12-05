package view.panels;

import view.canvas.*;

import javax.swing.*;
import java.awt.*;

public class CanvasBoard extends JPanel  {
    private MyCanvas canvas;

    public CanvasBoard(MyCanvas canvas) {
        this.canvas = canvas;
        setBackground(Color.WHITE);
        add(canvas);
    }

    public MyCanvas getCanvas() {
        return canvas;
    }
}
