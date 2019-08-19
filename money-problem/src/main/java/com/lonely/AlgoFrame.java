package com.lonely;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


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
    private int[] moneys;

    public void render(int[] moneys) {
        this.moneys = moneys;
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


            //平均每个节点的宽度
            if (moneys != null) {
                Arrays.sort(moneys);

                int width = canvasWidth / moneys.length;
                for (int i = 0; i < moneys.length; i++) {
                    int x = i * width + 1;
                    if (moneys[i] > 0) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
                        int y = canvasHeight / 2 - moneys[i];
                        AlgoVisHelper.fillRectangle(g2d, x, y, width - 1, moneys[i]);
                    } else {
                        //小于0，则使用红色标识
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                        AlgoVisHelper.fillRectangle(g2d, x, canvasHeight / 2, width - 1, -moneys[i]);
                    }
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


