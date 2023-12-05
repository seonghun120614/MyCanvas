package view.panels.tools;

import enums.*;
import view.canvas.*;
import view.panels.tools.buttons.*;

import javax.swing.*;
import java.awt.*;


public class UtilTool extends JPanel{
    public UtilTool(MyCanvas canvas) {
        for (UtilAuthority utilAuthority : UtilAuthority.values())
            add(new UtilButton(utilAuthority, canvas));
        setBackground(Color.WHITE);
    }
}