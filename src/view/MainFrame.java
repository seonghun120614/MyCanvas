package view;

import view.panels.*;
import view.panels.MenuBar;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame(ToolBar toolBar, CanvasBoard canvas, StatusBar statusBar, MenuBar menuBar) {
        setTitle("MyCanvas");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(720, 695);
        setJMenuBar(menuBar);
        add(toolBar);
        add(canvas);
        add(statusBar);
    }
}
