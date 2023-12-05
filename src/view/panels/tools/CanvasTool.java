package view.panels.tools;

import enums.*;
import view.canvas.*;
import view.panels.tools.buttons.*;

import javax.swing.*;
import java.awt.*;


public class CanvasTool extends JPanel {
    public CanvasTool(MyCanvas canvas) {
        for(CanvasAuthority canvasAuthority: CanvasAuthority.values())
            add(new CanvasButton(canvasAuthority, canvas));
        setBackground(Color.WHITE);
    }
}
