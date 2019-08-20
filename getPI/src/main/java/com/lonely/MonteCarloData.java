package com.lonely;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author ztkj-hzb
 * @Date 2019/8/19 16:22
 * @Description
 */
public class MonteCarloData {

    /**
     * 指定范围圆
     */
    private Circle circle;

    /**
     * 若干圆的圆心位置
     */
    private LinkedList<Point> points;

    /**
     * 绘制若干个圆
     */
    private int num;

    /**
     * 在圆内的个数
     */
    private int isInCirCleNum;

    public MonteCarloData(Circle circle) {
        this(circle, new LinkedList<>());
    }

    public MonteCarloData(Circle circle, LinkedList<Point> points) {
        this(circle, points, 0);
    }

    public MonteCarloData(Circle circle, LinkedList<Point> points, int num) {
        this.circle = circle;
        this.points = points;
        this.num = num;
        this.isInCirCleNum = 0;
    }

    /**
     * 添加节点
     */
    public void addPoint() {
        int x = (int) (Math.random() * (circle.getRadius() * 2));
        int y = (int) (Math.random() * (circle.getRadius() * 2));
        Point point = new Point(x, y);

        if (circle.container(point)) {
            isInCirCleNum++;
        }
        this.points.add(point);

    }

    /**
     * 计算pi值
     *
     * @return
     */
    public double getPi() {
        int circleArea = isInCirCleNum;
        int squreArea = points.size();
        double pi = 4 * (double) circleArea / squreArea;
        System.out.println("PI:" + pi);
        return pi;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIsInCirCleNum() {
        return isInCirCleNum;
    }

    public void setIsInCirCleNum(int isInCirCleNum) {
        this.isInCirCleNum = isInCirCleNum;
    }
}
