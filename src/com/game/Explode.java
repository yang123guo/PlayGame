package com.game;

import java.awt.*;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-05
 * @Description: 爆炸
 * @version: 1.0
 */
public class Explode {
    // 定义爆炸的初始位置
    double x, y;

    static Image[] imgs = new Image[16];
    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
            imgs[i].getWidth(null);
        }
    }

    int count;

    // 绘制16张图片
    public void draw(Graphics g) {
        if(count <= 15) {
            g.drawImage(imgs[count], (int) x, (int) y, null);
            count ++;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
