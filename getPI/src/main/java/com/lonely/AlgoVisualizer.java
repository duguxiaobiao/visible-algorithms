package com.lonely;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class AlgoVisualizer {

    /**
     * 数据
     */
    private MonteCarloData monteCarloData;


    /**
     * 视图
     */
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int num) {

        // 初始化数据
        if (sceneWidth != sceneHeight) {
            throw new RuntimeException("该demo需要设置 宽和高一致");
        }
        Circle circle = new Circle(sceneWidth / 2, sceneWidth / 2, sceneWidth / 2);
        this.monteCarloData = new MonteCarloData(circle, new LinkedList<>(), num);


        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("概率模拟", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        for (int i = 0; i < monteCarloData.getNum(); i++) {
            if (i % 100 == 0) {
                frame.render(this.monteCarloData);
                AlgoVisHelper.pause(10);
                //计算pi值
                if (this.monteCarloData.getPoints() != null && this.monteCarloData.getPoints().size() > 0) {
                    double pi = monteCarloData.getPi();
                    System.out.println("PI:" + pi);
                }
            }
            monteCarloData.addPoint();
        }


    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 1000;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 1000000);
    }
}
