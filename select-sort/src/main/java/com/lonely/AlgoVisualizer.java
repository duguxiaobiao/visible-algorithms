package com.lonely;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class AlgoVisualizer {

    /**
     * 数据
     */
    private SelectSortData selectSortData;

    /**
     * 视图
     */
    private AlgoFrame frame;

    private static int PARSE_TIME = 10;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int dataSize, int randomNum) {

        // 初始化数据
        this.selectSortData = new SelectSortData(randomNum, dataSize);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("选择排序可视化", sceneWidth, sceneHeight);
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

        setData(0, -1, -1);

        //进行选择排序
        if (this.selectSortData != null) {
            int[] nums = this.selectSortData.getNums();
            //比较多少轮
            for (int i = 0; i < nums.length; i++) {
                int minIndex = i;
                setData(i, -1, minIndex);
                //每轮比较处理
                for (int j = i; j < nums.length; j++) {
                    setData(i, j, minIndex);
                    if (nums[j] < nums[minIndex]) {
                        minIndex = j;
                        setData(i, j, minIndex);
                    }
                }

                //交换位置
                if (i != minIndex) {
                    this.selectSortData.swap(i, minIndex);
                    //渲染
                    setData(i + 1, -1, -1);
                }
            }
            setData(selectSortData.getSize(), -1, -1);
        }

    }

    private void setData(int orderedIndex, int currCompareIndex, int currMinIndex) {
        selectSortData.setCurrCompareIndex(currCompareIndex);
        selectSortData.setCurrMinIndex(currMinIndex);
        selectSortData.setOrderedIndex(orderedIndex);

        frame.render(selectSortData);
        AlgoVisHelper.pause(PARSE_TIME);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 500;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 100, 500);
    }
}
