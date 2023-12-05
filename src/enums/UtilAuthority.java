package enums;

import javax.swing.ImageIcon;


/* 그루핑, 색변경, 복사/붙여넣기 상수 */
public enum UtilAuthority {
    COPY(new ImageIcon("images/copy.png"), "Copy", 0),
    PASTE(new ImageIcon("images/paste.png"), "Paste", 1),
    DELETE(new ImageIcon("images/delete.png"), "Delete", 2),
    GROUP(new ImageIcon("images/group.png"), "Group", 3);

    private final ImageIcon icon;
    private final String name;
    private final int code;


    UtilAuthority(ImageIcon icon, String name, int code) {
        this.icon = icon;
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getCode() {
        return code;
    }
}
