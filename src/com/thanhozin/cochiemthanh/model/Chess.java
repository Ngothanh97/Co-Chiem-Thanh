package com.thanhozin.cochiemthanh.model;

import com.thanhozin.cochiemthanh.helper.Utils;
import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class Chess {
    public static final int SIZE = 70;
    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String WHITE_K = "k1";
    public static final String WHITE_L = "l1";
    public static final String WHITE_M = "m1";
    public static final String BLACK_O = "o2";
    public static final String BLACK_P = "p2";
    public static final String BLACK_Q = "q2";
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
        return "\nChess type: " + type + " x:" + getCoverX() + " y:" + getCoverY() + "\n";
    }

    public String info(){
        return x + "-" + y + "-" + type;
    }

    public int getX() {
        if (x < 40) {
            return 40;
        }
        return x;

    } // machine

    public int getY() {
        return y;
    }  // machine

    public int getXstt(){
        return Utils.chuyenXVeSoThuTu(getX());  // coder
    }

    public char getCoverX() {
        return Utils.coverXLocation(getX());
    }  // user

    public int getCoverY() {
        return Utils.chuyenYVeSoThuTu(y);
    } // user

    public String getType() {
        return String.valueOf(type);
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
            case WHITE_K:
                graphics2D.drawImage(ImageStore.IMG_WHITE_K_1, x, y, SIZE, SIZE, null);
                break;
            case WHITE_L:
                graphics2D.drawImage(ImageStore.IMG_WHITE_L_1, x, y, SIZE, SIZE, null);
                break;
            case WHITE_M:
                graphics2D.drawImage(ImageStore.IMG_WHITE_M_1, x, y, SIZE, SIZE, null);
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

    // get chess's team
    public char coverType() {
        if (type == null || type.isEmpty()) return ' ';
        if (type.contains("1")) return 'W';
        if (type.contains("2")) return 'B';
        return ' ';
    }
}
