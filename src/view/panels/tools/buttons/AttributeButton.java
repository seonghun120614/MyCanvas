package view.panels.tools.buttons;

import enums.*;
import view.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AttributeButton extends JButton {
    public AttributeButton(AttributeAuthority attributeAuthority, MyCanvas canvas) {
        setLayout(new BorderLayout());
        JButton button = new JButton(attributeAuthority.getIcon());
        JLabel label = new JLabel(attributeAuthority.getName());
        setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", Color.YELLOW);
                if (selectedColor != null)
                    canvas.changeStrokeColor(selectedColor);
            }
        });
        add(button, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }
}