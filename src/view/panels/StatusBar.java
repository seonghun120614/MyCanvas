package view.panels;

import view.canvas.*;
import view.panels.tools.*;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    MyCanvas canvas;
    CanvasLogger canvasLogger;

    public StatusBar(MyCanvas canvas, CanvasLogger canvasLogger, ZoomSlider zoomSlider) {
        Color color = new Color(215, 215, 215);
        setBackground(color);
        setLayout(new BorderLayout());
        this.canvasLogger = canvasLogger;
        this.canvas = canvas;
        setPreferredSize(new Dimension(710, 40));

        JPanel zoomSliderPanel = new JPanel(new BorderLayout());
        zoomSliderPanel.add(zoomSlider, BorderLayout.EAST);
        zoomSliderPanel.setBackground(color);
        zoomSliderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        add(canvasLogger, BorderLayout.WEST);
        add(zoomSliderPanel, BorderLayout.EAST);
    }
}