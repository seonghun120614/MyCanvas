package view.panels.menus.menuItems;

import view.canvas.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class SaveAs extends JMenuItem {
    public static String name;
    BufferedImage bufferedImage;

    public SaveAs(MyCanvas canvas) {
        super("Save as...", new ImageIcon("images/save.png"));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bufferedImage = getBufferedImageFromPanel(canvas);
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
                int rVal = jFileChooser.showSaveDialog(null);
                System.out.println(rVal);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    name = file.getAbsolutePath();
                    name = (name.endsWith(".png")) ? name : name+".png";
                    try {
                        ImageIO.write(bufferedImage, "png", new File(name));
                        System.out.println("saved Correctly " + file.getAbsolutePath());
                    } catch (IOException e1) {
                        System.out.println("Failed to save image");
                    }
                }
                if (rVal == JFileChooser.CANCEL_OPTION)
                    System.out.println("No file chosen");
            }
        });
    }

    public static BufferedImage getBufferedImageFromPanel(MyCanvas canvas) {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        canvas.paint(g);
        g.dispose();
        return image;
    }

    public String getName() {
        return name;
    }
}
