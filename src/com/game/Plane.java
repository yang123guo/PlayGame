package com.game;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-04
 * @Description: com.game
 * @version: 1.0
 */
public class Plane extends GameObject {
    int speed = 5;
    boolean left, right, up, down;
    // 飞机是否存活
    boolean live = true;

    public void drawSelf(Graphics  g){
        if(live) {
            g.drawImage(img, (int)x,(int) y, null);

            // 根据鼠标按键绘制坐标
            if(left) {
                x -= speed;
            }
            if(right) {
                x += speed;
            }
            if(up) {
                y -= speed;
            }
            if(down) {
                y += speed;
            }
        }
    }

    public Plane(Image  img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null) ;
        this.height = img.getHeight(null);
    }

    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void minusDirection(KeyEvent  e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}
