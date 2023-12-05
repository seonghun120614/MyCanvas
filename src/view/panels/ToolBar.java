package view.panels;

import view.panels.tools.*;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    public ToolBar(UtilTool utilTool, DrawTool drawTool, AttributeTool attributeTool, CanvasTool canvasTool) {
        add(new JLabel(new ImageIcon("images/partition.png")));
        add(utilTool);
        add(new JLabel(new ImageIcon("images/partition.png")));
        add(drawTool);
        add(new JLabel(new ImageIcon("images/partition.png")));
        add(attributeTool);
        add(new JLabel(new ImageIcon("images/partition.png")));
        add(canvasTool);
        add(new JLabel(new ImageIcon("images/partition.png")));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(710, 85));
    }
}