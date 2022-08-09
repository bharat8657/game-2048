package ua.pp.game;

import java.awt.*;

public class Tile {
    int value;

    public Tile() {
        this(0);
    }

    public Tile(int num) {
        value = num;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getForeground() {
        return value < 16 ? new Color(0x6E665F) : new Color(0xF5F2EF);
    }

    public Color getBackground() {
        switch (value) {
            case 2:
                return new Color(0xDCD2C9);
            case 4:
                return new Color(0xD2C8B6);
            case 8:
                return new Color(0xD59C6B);
            case 16:
                return new Color(0xDA8559);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xD95335);
            case 128:
                return new Color(0xCEB464);
            case 256:
                return new Color(0xCCB055);
            case 512:
                return new Color(0xCCAD46);
            case 1024:
                return new Color(0xCCAA38);
            case 2048:
                return new Color(0xC79D05);
        }
        return new Color(0xB9AEA2);
    }
}
