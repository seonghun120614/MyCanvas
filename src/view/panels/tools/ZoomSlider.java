package view.panels.tools;

import view.canvas.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

public class ZoomSlider extends JSlider {
    public ZoomSlider(MyCanvas canvas) {
        super(JSlider.HORIZONTAL, 100, 200, 100);
        setMajorTickSpacing(20);
        setMinorTickSpacing(5);
        setPreferredSize(new Dimension(200, 30));
        setPaintTicks(true);
        setPaintLabels(true);

        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (canvas != null)
                    canvas.setZoomFactor(getValue() / 100.0);
                repaint();
            }
        });

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(100, new JLabel("0"));
        labelTable.put(150, new JLabel("0.5"));
        labelTable.put(200, new JLabel("1"));
        setLabelTable(labelTable);
    }
}