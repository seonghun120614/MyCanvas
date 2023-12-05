import com.formdev.flatlaf.FlatLightLaf;
import view.MainFrame;
import view.canvas.CanvasLogger;
import view.canvas.MyCanvas;
import view.panels.CanvasBoard;
import view.panels.MenuBar;
import view.panels.StatusBar;
import view.panels.ToolBar;
import view.panels.tools.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CanvasLogger canvasLogger = new CanvasLogger();
                MyCanvas canvas = new MyCanvas(canvasLogger);
                canvasLogger.setCanvas(canvas);

                ToolBar toolBar = new ToolBar(
                        new UtilTool(canvas),
                        new DrawTool(canvas),
                        new AttributeTool(canvas),
                        new CanvasTool(canvas)
                );

                CanvasBoard board = new CanvasBoard(canvas);
                MenuBar menuBar = new MenuBar(canvas);
                StatusBar statusBar = new StatusBar(canvas, canvasLogger, new ZoomSlider(canvas));
                MainFrame mainFrame = new MainFrame(toolBar, board, statusBar, menuBar);
                mainFrame.setVisible(true);
            }
        });
    }
}
