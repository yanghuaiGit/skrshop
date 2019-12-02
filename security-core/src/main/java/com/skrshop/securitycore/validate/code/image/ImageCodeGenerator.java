package com.skrshop.securitycore.validate.code.image;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


@AllArgsConstructor
public class ImageCodeGenerator implements ValidateCodeGenerator {
    private SkrShopSecurityCenterProperties securityProperties;

    /**
     * 生成简单的验证码
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {
        // 先从请求中取,可以自定义设置宽度 设置图片宽度和高度
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        int length = securityProperties.getCode().getImage().getLength();
        // 干扰线条数
        int lines = 10;
        // 验证码数组
        int[] random = new int[length];
        // 定义用户保存验证码
        StringBuilder sysCode = new StringBuilder();
        Random r = new Random();
        BufferedImage b = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = b.getGraphics();
        g.setFont(new Font("宋体", Font.BOLD, 30));
        for (int i = 0; i < random.length; i++) {
            int number = r.nextInt(10);
            random[i] = number;
            // 10~40范围内的一个整数，作为y坐标
            int y = 10 + r.nextInt(40);
            // 随机颜色，RGB模式
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            // g.drawString("" + a, 5 + i * width / 4, y);写验证码
            g.drawString(Integer.toString(number), 5 + i * width / random.length, y);
            sysCode.append(random[i]);
        }
        for (int i = 0; i < lines; i++) {
            // 设置干扰线颜色
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
        g.dispose();
        return new ImageCode(b, sysCode.toString(), securityProperties.getCode().getImage().getExpireIn());
    }


}
