package com.thanhozin.cochiemthanh.model;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class Chess {
    public static final int SIZE = 70;
    public static final String WHIlE ="while";
    public static final String BLACK ="black";
    public static final String WHILE_K = "k1";
    public static final String WHILE_L = "l1";
    public static final String WHILE_M = "m1";
    public static final String BLACK_O = "o1";
    public static final String BLACK_P = "p1";
    public static final String BLACK_Q = "q1";
    private int x;
    private int y;
    private String type;

    public Chess(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        Chess c = (Chess) o;
        return c.getType().equalsIgnoreCase(((Chess) o).getType()) && c.getX() == ((Chess) o).getX() && c.getY() == ((Chess) o).getY();
    }

    @Override
    public String toString() {
        return "\nChess type: " + type + " x:" + x + " y:" + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return String.valueOf(coverType(type));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void drawChess(Graphics2D graphics2D) {
        switch (type) {
            case WHILE_K:
                graphics2D.drawImage(ImageStore.IMG_WHILE_K_1, x, y, SIZE, SIZE, null);
                break;
            case WHILE_L:
                graphics2D.drawImage(ImageStore.IMG_WHILE_L_1, x, y, SIZE, SIZE, null);
                break;
            case WHILE_M:
                graphics2D.drawImage(ImageStore.IMG_WHILE_M_1, x, y, SIZE, SIZE, null);
                break;
            case BLACK_O:
                graphics2D.drawImage(ImageStore.IMG_BLACK_O_1, x, y, SIZE, SIZE, null);
                break;
            case BLACK_P:
                graphics2D.drawImage(ImageStore.IMG_BLACK_P_1, x, y, SIZE, SIZE, null);
                break;
            case BLACK_Q:
                graphics2D.drawImage(ImageStore.IMG_BLACK_Q_1, x, y, SIZE, SIZE, null);
                break;
            default:
                break;

        }
    }

    public char coverType(String type) {
        switch (type) {
            case WHILE_K:
            case WHILE_L:
            case WHILE_M:
                return 'W';
            case BLACK_O:
            case BLACK_P:
            case BLACK_Q:
                return 'B';
            default:
                return ' ';
        }
    }
}
