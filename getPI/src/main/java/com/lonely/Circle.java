package com.lonely;

import java.awt.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/19 14:25
 * @Description 圆
 */
public class Circle {

    /**
     * 圆心的横纵坐标
     */
    private int x, y;

    /**
     * 圆半径大小
     */
    private int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * 判断指定点是否在园内
     *
     * @param point
     * @return
     */
    public boolean container(Point point) {
        return Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2) <= Math.pow(radius, 2);
    }

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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
