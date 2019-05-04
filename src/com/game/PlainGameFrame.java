package com.game;

import javax.swing.*;
import java.awt.*;
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
    Image ball = GameUtil.getImage("/images/ball.png");

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
    }

    /**
     * 功能描述: 自动被调用的方法
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    public void paint(Graphics g) {
        g.drawRect(100, 100, 300, 300);
        g.drawImage(ball, 250, 250, null);
    }

    public static void main(String[] args) {
        PlainGameFrame f = new PlainGameFrame();
        f.launchFrame();
    }
}
