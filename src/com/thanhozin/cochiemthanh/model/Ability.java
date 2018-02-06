package com.thanhozin.cochiemthanh.model;

import com.thanhozin.cochiemthanh.manager.ImageStore;

import java.awt.*;

/**
 * Created by ThanhND on 10/23/2017.
 * gợi ý nước đi
 */
public class Ability {
    private static final int SIZE = 76;
    // x, y theo may
    private int x;
    private int y;

    public Ability(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawAbility(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_ABILITY, x, y, SIZE, SIZE, null);
    }

}
