package com.game;

import java.awt.*;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-04
 * @Description: com.game
 * @version: 1.0
 */
public class GameObject {
    Image img;
    double x, y;
    int speed;
    int width, height;

    // 根据图片和位置信息绘制到画布上
    public void drawSelf(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }

    // 构造函数
    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    // 构造函数2
    public GameObject(Image img, double x, double y) {
        super();
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }

    /**
     * 功能描述: 绘制正方形
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    public Rectangle getRect() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
