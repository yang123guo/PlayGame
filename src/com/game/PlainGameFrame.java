package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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

    Plane plane = new Plane(planeImg, Constant.PLANE_X, Constant.PLANE_Y);
    Shell[] shells = new Shell[20];

    Explode explode;
    Date startTime = new Date();
    Date endTime;
    int period;

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        // 设置窗口的标题
        this.setTitle("飞机大战");
        // 显示窗口
        this.setVisible(true);
        // 设置窗口大小
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
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
        // 绘制50个炮弹
        for(int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
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
        // 保存最初的画笔颜色
        Color c = g.getColor();

        // 绘制背景图
        g.drawImage(bg, 0, 0, null);

        // 绘制飞机
        plane.drawSelf(g);


        for(Shell shell : shells) {
            // 绘制炮弹
            if(shell != null) {
                shell.draw(g);

                // 炮弹碰到飞机(边界重叠) 飞机挂掉
                boolean isHit = shell.getRect().intersects(plane.getRect());

                if(isHit && plane.live) {
                    plane.live = false;
                    // 如果爆炸对象不存在，则创建
                    if(explode == null) {
                        explode = new Explode(plane.x, plane.y);
                        endTime = new Date();
                        period = (int)((endTime.getTime() - startTime.getTime()) / 1000);
                    }
                    explode.draw(g);
                }

                // 如果飞机挂了
                if(!plane.live) {
                    g.setColor(Color.red);
                    Font f = new Font("宋体", Font.BOLD, 20);
                    g.setFont(f);
                    g.drawString("您一共生存了：" + period + "秒", Constant.FONT_X, Constant.FONT_Y);

                }
            }
        }

        g.setColor(c);
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
                    // 间隔时间
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

    // 配置双缓冲 增加流畅度
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
