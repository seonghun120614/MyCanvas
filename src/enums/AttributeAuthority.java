package enums;

import javax.swing.ImageIcon;


public enum AttributeAuthority {
    LINE_COLOR(new ImageIcon("images/color.png"), "Line", 20);

    private final ImageIcon icon;
    private final String name;
    private final int code;

    AttributeAuthority(ImageIcon icon, String name, int code) {
        this.icon = icon;
        this.name = name;
        this.code = code;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
