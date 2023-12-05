package view.panels.tools;

import enums.*;
import view.canvas.*;
import view.panels.tools.buttons.*;

import javax.swing.*;
import java.awt.*;


public class DrawTool extends JPanel{
    public DrawTool(MyCanvas canvas) {
        setLayout(new GridLayout(0, 4, 10, 10));
        for (DrawAuthority drawAuthority : DrawAuthority.values())
            add(new DrawButton(drawAuthority, canvas));
        setBackground(Color.WHITE);
    }
}


