package view.panels.tools;

import enums.*;
import view.canvas.*;
import view.panels.tools.buttons.*;

import javax.swing.*;
import java.awt.*;


public class AttributeTool extends JPanel{
    public AttributeTool(MyCanvas canvas) {
        for(AttributeAuthority attributeAuthority: AttributeAuthority.values())
            add(new AttributeButton(attributeAuthority, canvas));
        setBackground(Color.WHITE);
    }
}