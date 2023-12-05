package view.panels.menus.menuItems;

import view.canvas.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Open extends JMenuItem {
    private final MyCanvas canvas;

    public Open(MyCanvas canvas) {
        super("Open", new ImageIcon("images/file.png"));
        this.canvas = canvas;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadImage(selectedFile);
                }
            }
        });
    }

    private void loadImage(File selectedFile) {
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            canvas.loadImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}