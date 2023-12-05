package view.panels.tools.buttons;

import enums.*;
import view.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UtilButton extends JPanel {
    public UtilButton(UtilAuthority utilAuthority, MyCanvas canvas) {
        setLayout(new BorderLayout());
        JButton button = new JButton(utilAuthority.getIcon());
        JLabel label = new JLabel(utilAuthority.getName());
        setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(utilAuthority.getCode() == 0)
                    canvas.copy();
                if(utilAuthority.getCode() == 1)
                    canvas.paste();
                if(utilAuthority.getCode() == 2)
                    canvas.delete();
                if(utilAuthority.getCode() == 3)
                    canvas.grouping();
            }
        });
        add(button, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }
}