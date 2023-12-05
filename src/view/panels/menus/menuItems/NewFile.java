package view.panels.menus.menuItems;

import view.canvas.*;

import javax.swing.*;
import java.awt.event.*;

public class NewFile extends JMenuItem{
    public NewFile(MyCanvas canvas) {
        super("New File", new ImageIcon("images/file.png"));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveAs.name = null;
                canvas.init();
                canvas.loadImage(null);
                canvas.repaint();
            }
        });
    }
}
