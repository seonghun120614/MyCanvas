package view.panels.menus;

import view.canvas.*;
import view.panels.menus.menuItems.*;

import javax.swing.*;


public class FileMenu extends JMenu {
    public FileMenu(MyCanvas canvas) {
        super("File");
        add(new NewFile(canvas));
        add(new Open(canvas));
        add(new Save(canvas));
        add(new SaveAs(canvas));
    }
}