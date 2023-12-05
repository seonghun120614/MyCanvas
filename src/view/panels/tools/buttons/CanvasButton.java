package view.panels.tools.buttons;

import enums.*;
import view.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CanvasButton extends JButton{
    public CanvasButton(CanvasAuthority canvasAuthority, MyCanvas canvas) {
        super(canvasAuthority.getIcon());
        setBackground(Color.WHITE);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(canvasAuthority.getCode() == 30)
                    canvas.undo();
                if(canvasAuthority.getCode() == 31)
                    canvas.redo();
            }
        });
    }
}
