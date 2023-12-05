package view.panels.tools.buttons;

import enums.*;
import view.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class DrawButton extends JButton{
    public static ArrayList<DrawButton> db = new ArrayList<>();
    private static final Color ACTIVE_COLOR = Color.decode("#D3D3D3");

    public DrawButton(DrawAuthority drawAuthority, MyCanvas canvas) {
        super(drawAuthority.getIcon());
        db.add(this);
        setBackground(Color.WHITE);
        setFocusPainted(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setCode(drawAuthority.getCode());
                if (DrawButton.this.getBackground() == ACTIVE_COLOR) {
                    DrawButton.this.setBackground(Color.WHITE);
                } else {
                    for(DrawButton d : db) d.setBackground(Color.WHITE);
                    DrawButton.this.setBackground(ACTIVE_COLOR);
                }
                SwingUtilities.updateComponentTreeUI(DrawButton.this);
            }
        });
    }
}
