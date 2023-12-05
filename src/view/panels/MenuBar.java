package view.panels;

import view.canvas.*;
import view.panels.menus.*;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar(MyCanvas canvas) {
        this.setBackground(Color.WHITE);
        add(new FileMenu(canvas));
        add(new EditMenu());
    }
}
