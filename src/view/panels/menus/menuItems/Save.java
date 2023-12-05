package view.panels.menus.menuItems;

import view.canvas.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Save extends JMenuItem {
    public Save(MyCanvas canvas) {
        super("Save", new ImageIcon("images/save.png"));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SaveAs.name != null) {
                    try {
                        ImageIO.write(SaveAs.getBufferedImageFromPanel(canvas), "png", new File(SaveAs.name));
                        System.out.println("saved Correctly");
                    } catch (IOException e1) {
                        System.out.println("Failed to save image");
                    }
                } else {
                    saveAsImage(canvas);
                }
            }
        });
    }

    private void saveAsImage(MyCanvas canvas) {
        BufferedImage bufferedImage = SaveAs.getBufferedImageFromPanel(canvas);
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));

        int rVal = jFileChooser.showSaveDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            SaveAs.name = file.getAbsolutePath();
            SaveAs.name = (SaveAs.name.endsWith(".png")) ? SaveAs.name : SaveAs.name + ".png";
            try {
                ImageIO.write(bufferedImage, "png", new File(SaveAs.name));
                System.out.println("Saved correctly: " + file.getAbsolutePath());
            } catch (IOException e1) {
                System.out.println("Failed to save image");
            }
        }
        if (rVal == JFileChooser.CANCEL_OPTION)
            System.out.println("No file chosen");
    }
}
