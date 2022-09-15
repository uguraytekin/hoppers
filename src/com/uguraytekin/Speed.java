package com.uguraytekin;

/**
 * @Author: Ugur Aytekin
 * @create: 15.09.2022
 */

public class Speed {
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;

    public Speed(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
