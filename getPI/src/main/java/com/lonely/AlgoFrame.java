package com.lonely;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


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


    //设置自己的数据
    private MonteCarloData monteCarloData;

    public void render(MonteCarloData monteCarloData) {
        this.monteCarloData = monteCarloData;
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

            //限制线条宽度
            AlgoVisHelper.setStrokeWidth(g2d, 3);
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);

            if(monteCarloData == null){
                return;
            }

            Circle circle = monteCarloData.getCircle();
            LinkedList<Point> points = monteCarloData.getPoints();

            if (circle != null && points != null) {
                AlgoVisHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getRadius());
                for (int i = 0; i < points.size(); i++) {
                    Point point = points.get(i);
                    //判断生成的点是否在园内
                    if (circle.container(point)) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                    } else {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Green);
                    }
                    AlgoVisHelper.fillCircle(g2d, point.x, point.y, 3);
                }
            }


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


