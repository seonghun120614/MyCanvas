package view.canvas;

import entities.Rectangle;
import entities.Shape;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;


public class MyCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private static ArrayList<Shape> SHAPES = new ArrayList<>();
    private static ArrayList<Group> GROUPS = new ArrayList<>();
    private static ArrayList<Shape> CLIP_BOARD = new ArrayList<>();
    private static ArrayList<Shape> SELECTED_SHAPES = new ArrayList<>();
    private static Rectangle2D SELECTION_AREA = new Rectangle2D.Double();
    private CanvasLogger canvasLogger;
    private int code;
    private Shape tmp;
    private int startX, startY, endX, endY;
    private double zoomFactor = 1.0;
    private Selection selection;
    private BufferedImage loadedImage;

    public MyCanvas(CanvasLogger canvasLogger) {
        this.canvasLogger = canvasLogger;
        setPreferredSize(new Dimension(700, 490));
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {handleKeyPress(e);}
        });

        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undoAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redoAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copyAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "pasteAction");

        actionMap.put("undoAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });
        actionMap.put("redoAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });
        actionMap.put("copyAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copy();
            }
        });
        actionMap.put("pasteAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paste();
            }
        });
        setFocusable(true);
        requestFocus();
    }

    public void init() {
        canvasLogger.init();
        tmp = null;
        SHAPES = new ArrayList<>();
        GROUPS = new ArrayList<>();
        CLIP_BOARD = new ArrayList<>();
        SELECTED_SHAPES = new ArrayList<>();
        SELECTION_AREA = new Rectangle2D.Double();
        zoomFactor = 1.0;
        setPreferredSize(new Dimension(700, 490));
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { handleKeyPress(e); }
        });
        setFocusable(true);
        requestFocus();
    }

    public void handleKeyPress(KeyEvent e) {
        if (e.isControlDown()) {
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                undo();
            } else if (e.getKeyCode() == KeyEvent.VK_Y) {
                redo();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                copy();
            } else if (e.getKeyCode() == KeyEvent.VK_V) {
                paste();
            }
        }
    }

    public void setCode(int code) {
        this.code = code!= this.code ? code:-1;
    }

    public Shape getShape() {
        if(code == 10) {
            return new Line(startX, startY);
        } else if(code == 11) {
            return new Ellipse(startX, startY);
        } else if(code == 12) {
            return new Rectangle(startX, startY);
        } else if(code == 13) {
            return new Triangle(startX, startY);
        } else if(code == 14) {
            return new Pencil(startX, startY);
        } else if(code == 15) {
            return new Trapezoid(startX, startY);
        } else if(code == 16) {
            return new Pentagon(startX, startY);
        } else if(code == 17) {
            return new Hexagon(startX, startY);
        }
        return null;
    }

    public void changeStrokeColor(Color color) {
        for(Shape shape : SELECTED_SHAPES)
            shape.change(color);
        writeLog("changeStrokeColor");
        repaint();
    }

    public void delete() {
        tmp = null;
        for(Shape s : SELECTED_SHAPES) {
            if (s.getClass().getName().equals("entities.Group")) SHAPES.removeAll(((Group) s).getShapes());
        }
        for(int i=GROUPS.size()-1; i>=0; i--) {
            GROUPS.get(i).remove(SELECTED_SHAPES);
            if(GROUPS.get(i).getSize() <= 1) GROUPS.remove(i);
        }
        SHAPES.removeAll(SELECTED_SHAPES);
        SELECTED_SHAPES.clear();
        writeLog("delete");
        repaint();
    }

    public void copy() {
        CLIP_BOARD.clear();
        if(SELECTED_SHAPES.get(0).getClass().getName().equals("entities.Group"))
            CLIP_BOARD.addAll(((Group) SELECTED_SHAPES.get(0)).getShapes());
        else
            CLIP_BOARD.addAll(SELECTED_SHAPES);
    }

    public void paste() {
        if(CLIP_BOARD.size() == 0) return;
        SELECTED_SHAPES.clear();
        for(Shape shape: CLIP_BOARD) {
            String name = shape.getClass().getName();
            if (name.equals("entities.Rectangle")) {
                tmp = new Rectangle(shape);
            } else if (name.equals("entities.Line")) {
                tmp = new Line(shape);
            } else if (name.equals("entities.Triangle")) {
                tmp = new Triangle(shape);
            } else if (name.equals("entities.Trapezoid")) {
                tmp = new Trapezoid(shape);
            } else if (name.equals("entities.Pentagon")) {
                tmp = new Pentagon(shape);
            } else if (name.equals("entities.Hexagon")) {
                tmp = new Hexagon(shape);
            } else if (name.equals("entities.Ellipse")) {
                tmp = new Ellipse(shape);
            } else if (name.equals("entities.Pencil")) {
                tmp = new Pencil(shape);
            }
            SHAPES.add(tmp);
            SELECTED_SHAPES.add(tmp);
        }
        copy();
        writeLog("paste");
        repaint();
    }

    public void grouping() {
        if(SELECTED_SHAPES.size() <= 1) return;
        Group group = new Group(SELECTED_SHAPES);
        SELECTED_SHAPES.clear();
        GROUPS.add(group);
        SELECTED_SHAPES.add(group);
        writeLog("grouping");
        repaint();
    }

    public void undo() {
        SELECTED_SHAPES.clear();
        tmp = null;
        canvasLogger.undo(SHAPES, GROUPS);
        repaint();
    }

    public void redo() {
        SELECTED_SHAPES.clear();
        tmp = null;
        canvasLogger.redo(SHAPES, GROUPS);
        repaint();
    }


    public void activeIfContain() {
        for(int i = SHAPES.size()-1; i >= 0; i--)
            if(SHAPES.get(i).in(SELECTION_AREA)) SELECTED_SHAPES.add(SHAPES.get(i));
    }

    public void activeIfContain(int x, int y) {
        for(int i = SHAPES.size()-1; i >= 0; i--) {
            if (SHAPES.get(i).contains(x, y)) {
                for(int j = GROUPS.size()-1; j>=0; j--) {
                    if (GROUPS.get(j).containsShape(SHAPES.get(i))) {
                        SELECTED_SHAPES.add(GROUPS.get(j));
                        return;
                    }
                }
                SELECTED_SHAPES.add(SHAPES.get(i));
                return;
            }
        }
    }
    
    public void setZoomFactor(double factor) {
        zoomFactor = factor;
        repaint(); 
    }

    private void writeLog(String instruction) {
        for(Shape shape: SELECTED_SHAPES) {
            if(SHAPES.contains(shape)){
                canvasLogger.append(SHAPES.indexOf(shape));
                canvasLogger.append(shape.getClass().getName());
            }
        }
        canvasLogger.append(instruction);
        canvasLogger.logging(SHAPES, GROUPS);
    }

    public void loadImage(BufferedImage image) {
        loadedImage = image;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(zoomFactor, zoomFactor);
        super.paint(g);
        if(tmp != null) tmp.draw(g2d);
        for(Shape shape: SHAPES) shape.draw(g2d);
        for(Shape shape: SELECTED_SHAPES) shape.select(g2d); 
        if(this.selection != null) selection.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SELECTED_SHAPES.clear();
        activeIfContain(e.getX(), e.getY());
        repaint();
    }

    // 마우스 누를때
    @Override
    public void mousePressed(MouseEvent e) {
        SELECTED_SHAPES.clear();
        startX = (int) (e.getX() / zoomFactor);
        startY = (int) (e.getY() / zoomFactor);
        endX = (int) (e.getX() / zoomFactor);
        endY = (int) (e.getY() / zoomFactor);
        tmp = getShape();
        repaint();
    }

    // 마우스 땔때
    @Override
    public void mouseReleased(MouseEvent e) {
        endX = (int) (e.getX() / zoomFactor);
        endY = (int) (e.getY() / zoomFactor);

        if(Math.abs(endX - startX) < 10 && Math.abs(endY - startY) < 10) {
            endX = startX + 10;
            endY = startY + 10;
        }
        if(tmp != null) {
            tmp.set(endX, endY);
            tmp.update();
            tmp.connect();
            SHAPES.add(tmp);
            SELECTED_SHAPES.add(tmp);
            writeLog("create");
        } else {
            SELECTION_AREA.setRect(
                    Math.min(startX, endX),
                    Math.min(startY, endY),
                    Math.abs(endX - startX),
                    Math.abs(endY - startY)
            );
            activeIfContain();
        }
        this.selection = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endX = (int) (e.getX() / zoomFactor);
        endY = (int) (e.getY() / zoomFactor);

        if(tmp != null) {
            tmp.set(endX, endY);
            tmp.update();
            tmp.connect();
        } else {
            this.selection = new Selection(startX, startY);
            this.selection.set(endX, endY);
            this.selection.update();
            this.selection.connect();
            SELECTION_AREA.setRect(
                    Math.min(startX, endX),
                    Math.min(startY, endY),
                    Math.abs(endX - startX),
                    Math.abs(endY - startY)
            );
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (loadedImage != null) {
            g2d.drawImage(loadedImage, 0, 0, this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}