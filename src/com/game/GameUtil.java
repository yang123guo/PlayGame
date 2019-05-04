package com.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @Auther: yangguoqiang01
 * @Date: 2019-05-04
 * @Description: com.game
 * @version: 1.0
 */
public class GameUtil {

    // 工具类最好私有化
    private GameUtil() {
    }

    /**
     * 功能描述: 返回指向路径的图片对象
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
