package enums;

import javax.swing.ImageIcon;


public enum CanvasAuthority {
    UNDO(new ImageIcon("images/undo.png"), 30),
    REDO(new ImageIcon("images/redo.png"), 31);


    private final ImageIcon icon;
    private final int code;

    CanvasAuthority(ImageIcon icon, int code) {
        this.icon = icon;
        this.code = code;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getCode() {
        return code;
    }
}
