package view.canvas;

import entities.*;

import javax.swing.*;
import java.util.*;


public class CanvasLogger extends JLabel {
    private String TEMP = "";
    private static Stack<String> MSG_STACK;
    private static Stack<ArrayList<Shape>> LOG_SHAPE_STACK, REDO_SHAPE_STACK;
    private static Stack<ArrayList<Group>> LOG_GROUP_STACK, REDO_GROUP_STACK;
    private MyCanvas canvas; // DO NOT REMOVE THIS CODE

    public CanvasLogger() {
        super();
        init();
    }

    public void setCanvas(MyCanvas canvas) {
        this.canvas = canvas;
    }

    public void init() {
        TEMP = "";
        MSG_STACK = new Stack<>();
        LOG_SHAPE_STACK = new Stack<>();
        LOG_GROUP_STACK = new Stack<>();
        REDO_SHAPE_STACK = new Stack<>();
        REDO_GROUP_STACK = new Stack<>();

        setText("New Status Bar On");
        setSize(570, 20);
    }

    public void logging(ArrayList<Shape> shapes, ArrayList<Group> groups) {
        setText(TEMP);
        ArrayList<Shape> tmp1 = new ArrayList<>();
        for(Shape shape: shapes) {
            String name = shape.getClass().getName();
            switch (name) {
                case "entities.Rectangle" -> tmp1.add(new Rectangle(shape, false));
                case "entities.Line" -> tmp1.add(new Line(shape, false));
                case "entities.Triangle" -> tmp1.add(new Triangle(shape, false));
                case "entities.Trapezoid" -> tmp1.add(new Trapezoid(shape, false));
                case "entities.Pentagon" -> tmp1.add(new Pentagon(shape, false));
                case "entities.Hexagon" -> tmp1.add(new Hexagon(shape, false));
                case "entities.Ellipse" -> tmp1.add(new Ellipse(shape, false));
                case "entities.Pencil" -> tmp1.add(new Pencil(shape, false));
            }
        }
        LOG_SHAPE_STACK.add(tmp1);

        ArrayList<Group> tmp2 = new ArrayList<>(groups);
        for(Group group: groups)
            tmp2.add(new Group(group, false));
        LOG_GROUP_STACK.add(tmp2);
        TEMP = "";
    }

    public void undo(ArrayList<Shape> shapes, ArrayList<Group> groups) {
        if(LOG_SHAPE_STACK.size() == 0) return;
        REDO_SHAPE_STACK.add(LOG_SHAPE_STACK.pop());
        REDO_GROUP_STACK.add(LOG_GROUP_STACK.pop());
        apply(shapes, groups);
    }

    public void redo(ArrayList<Shape> shapes, ArrayList<Group> groups) {
        if(REDO_SHAPE_STACK.size() == 0) return;
        LOG_SHAPE_STACK.add(REDO_SHAPE_STACK.pop());
        LOG_GROUP_STACK.add(REDO_GROUP_STACK.pop());
        apply(shapes, groups);
    }

    private void apply(ArrayList<Shape> shapes, ArrayList<Group> groups) {
        shapes.clear(); groups.clear();
        if(LOG_SHAPE_STACK.size() == 0) return;
        shapes.addAll(LOG_SHAPE_STACK.get(LOG_SHAPE_STACK.size()-1));
        groups.addAll(LOG_GROUP_STACK.get(LOG_GROUP_STACK.size()-1));
    }

    public void append(String logMsg) {
        TEMP += "\t" + logMsg;
    }

    public void append(int i) {
        TEMP += "\t" + String.format("%d", i);
    }

    public void update() {
        setText(MSG_STACK.get(MSG_STACK.size()-1));
    }
}

