package com.lonely;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    /**
     * 数据
     */
    private int[] moneys;
    /**
     * 视图
     */
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {

        // 初始化数据
        int m = 100;
        moneys = new int[m];
        for (int i = 0; i < m; i++) {
            moneys[i] = 100;
        }

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

        // TODO: 编写自己的动画逻辑
        while (true) {
            //赋值
            frame.render(moneys);

            //休眠
            AlgoVisHelper.pause(10);

            //随机给别人一块钱
            for (int j = 0; j < 50; j++) {
                for (int i = 0; i < moneys.length; i++) {
                    //if (moneys[i] > 0) {
                        //随机一位人
                        int randomIndex = (int) (Math.random() * moneys.length);
                        moneys[i] -= 1;
                        moneys[randomIndex] += 1;
                    //}
                }
            }
        }

    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 1024;
        int sceneHeight = 468;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
