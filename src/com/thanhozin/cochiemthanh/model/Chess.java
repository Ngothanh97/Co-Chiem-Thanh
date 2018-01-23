package com.thanhozin.cochiemthanh.model;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 */
public class Chess {
    public static final int SIZE = 70;
    public static final String WHITE ="white";
    public static final String BLACK ="black";
    public static final String WHITE_K = "k1";
    public static final String WHITE_L = "l1";
    public static final String WHITE_M = "m1";
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
        return "\nChess type: " + type + " x:" + chuyen_X_Ve_So_Thu_Tu(x) + " y:" + chuyen_y_ve_so_thu_tu(y) + "\n";
    }

    /*
    Cover chuyển đổi tọa độ quân cờ
    <------------------------------------------------------------------------>
     */
    private int chuyen_X_Ve_So_Thu_Tu(int xLocation) {
        if (xLocation >= 34 && xLocation < 111) {
            return 1;
        }
        if (xLocation >= 114 && xLocation < 188) {
            return 2;
        }
        if (xLocation >= 192 && xLocation < 268) {
            return 3;
        }
        if (xLocation >= 271 && xLocation < 348) {
            return 4;
        }
        if (xLocation >= 351 && xLocation < 428) {
            return 5;
        }
        if (xLocation >= 430 && xLocation < 508) {
            return 6;
        }
        if (xLocation >= 510 && xLocation < 584) {
            return 7;
        }
        if (xLocation >= 587 && xLocation < 665) {
            return 8;
        }
        return 0;
    }

    private int chuyen_y_ve_so_thu_tu(int yLocation) {
        if (yLocation >= 35 && yLocation < 110) {
            return 1;
        }
        if (yLocation >= 113 && yLocation < 191) {
            return 2;
        }
        if (yLocation >= 193 && yLocation < 270) {
            return 3;
        }
        if (yLocation >= 272 && yLocation < 349) {
            return 4;
        }
        if (yLocation >= 352 && yLocation < 428) {
            return 5;
        }
        if (yLocation >= 430 && yLocation < 507) {
            return 6;
        }
        if (yLocation >= 510 && yLocation < 585) {
            return 7;
        }
        if (yLocation >= 588 && yLocation < 665) {
            return 8;
        }
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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

    public char coverType(String type) {
        switch (type) {
            case WHITE_K:
            case WHITE_L:
            case WHITE_M:
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
