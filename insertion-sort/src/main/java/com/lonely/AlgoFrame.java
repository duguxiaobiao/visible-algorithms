package com.lonely;

import javax.swing.*;
import java.awt.*;


/**
 * 视图层
 *
 * @author ztkj
 */
public class AlgoFrame extends JFrame {

    /**
     * 窗体宽度
     */
    private int canvasWidth;
    /**
     * 窗体高度
     */
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        //设置面板
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        //设置窗体大小跟随面板大小变化
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }


    // TODO: 设置自己的数据
    private InsertionSortData insertionSortData;


    public void render(InsertionSortData insertionSortData) {
        this.insertionSortData = insertionSortData;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            // TODO： 绘制自己的数据data
            if (insertionSortData == null) {
                return;
            }

            //限制线条宽度
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);

            int dataSize = insertionSortData.getSize();
            int avgWidth = getCanvasWidth() / dataSize;

            for (int i = 0; i < dataSize; i++) {
                //设置颜色
                if(i < insertionSortData.getOrderedIndex()){
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Orange);
                }else{
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Grey);
                }

                if(i == insertionSortData.getCurrCompareIndex()){
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.LightBlue);
                }

                if(i == insertionSortData.getCurrMinIndex()){
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Indigo);
                }

                int x = i * avgWidth;
                int y = getCanvasHeight() - insertionSortData.getNums()[i];
                AlgoVisHelper.fillRectangle(g2d, x, y, avgWidth - 1, insertionSortData.getNums()[i]);
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


