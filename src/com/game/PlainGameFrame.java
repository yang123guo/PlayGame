package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-04
 * @Description: 飞机游戏的窗口
 * @version: 1.0
 */
public class PlainGameFrame extends JFrame {

    /**
     * 功能描述: 初始化一个球
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    int planeX = 250, planeY = 250;

    Plane plane = new Plane(planeImg, planeX, planeY);

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        // 设置窗口的标题
        this.setTitle("飞机大战");
        // 显示窗口
        this.setVisible(true);
        // 设置窗口大小
        this.setSize(500, 500);
        // 设置窗口的起始位置
        this.setLocation(0, 0);

        // 内部匿名类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // super.windowClosing(e);
                System.exit(0);
            }
        });
        // 启动重绘线程
        new PaintThread().start();
        // 监听键盘事件
        addKeyListener(new KeyMonitor());
    }

    /**
     * 功能描述: 自动被调用的方法，不是用户自己调用的（钩子）
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);
    }

    /**
     * 功能描述: 反复重绘
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // 重绘窗口
                repaint();

                try {
                    //
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }

    public static void main(String[] args) {
        PlainGameFrame f = new PlainGameFrame();
        f.launchFrame();
    }
}
