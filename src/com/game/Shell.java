package com.game;

import java.awt.*;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-05
 * @Description: 炮弹类
 * @version: 1.0
 */
public class Shell extends GameObject {
    double degree;

    public Shell() {
        // 炮弹本身的描述
        x = 200;
        y = 200;
        height = 10;
        width = 10;
        speed = 3;
        // 弧度是 0到2π 随机数
        degree = Math.random() * Math.PI * 2;
    }

    public void draw(Graphics g) {
        // 保存最初的画笔颜色
        Color c = g.getColor();

        g.setColor(Color.YELLOW);

        // 画图形
        g.fillOval((int)x, (int) y, width, height);

        // 随机角度飞行
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        // 边界处理
        if(x < 0 || x > Constant.GAME_WIDTH - width) {
            // 水平碰到边界折射
            degree  = Math.PI - degree;
        }

        if(y < Constant.GAME_TITLE_HEIGHT || y > Constant.GAME_HEIGHT - height){
            degree = - degree;
        }

        // 归还画笔颜色
        g.setColor(c);
    }
}
