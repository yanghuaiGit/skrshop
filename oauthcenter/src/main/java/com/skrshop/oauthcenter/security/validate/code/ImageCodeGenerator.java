package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.oauthcenter.security.config.properties.ImageCodeProperties;
import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    @Override
    public ValidateCode generateCode(HttpServletRequest httpServletRequest) {
        ImageCodeProperties image = skrShopAuthorityCenterProperties.getSecurity().getCode().getImage();
        int width = image.getWidth();
        int height = image.getHeight();
        int codeCount = image.getCodeCount();
        int lineCount = image.getLineCount();
        ImageCode imageCode = new ImageCode();
        //每个字符的宽度(左右各空出一个字符) 字体的高度
        int x = width / codeCount - 6, fontHeight = height - 2, codeY = height - 4;
        int red, green, blue;

        // 图像buffer
        imageCode.setBuffImg(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
        Graphics2D g = imageCode.getBuffImg().createGraphics();

        // 将图像背景填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 增加下面代码使得背景透明
        //  imageCode.setBuffImg(g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT));
        g.dispose();
        g = imageCode.getBuffImg().createGraphics();
        // 背景透明代码结束

        // 画图BasicStroke是JDK中提供的一个基本的画笔类,我们对他设置画笔的粗细，就可以在drawPanel上任意画出自己想要的图形了。
        g.setColor(new Color(255, 0, 0));
        g.setStroke(new BasicStroke(1f));
        g.fillRect(128, 128, width, height);

        // 生成随机数
        Random random = new Random();
        //设置字体类型、字体大小、字体样式　
        Font font = new Font("微软雅黑", Font.PLAIN, fontHeight);

        g.setFont(font);

        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            int xs = random.nextInt(width);//x坐标开始
            int ys = random.nextInt(height);//y坐标开始
            int xe = xs + random.nextInt(width << 3);//x坐标结束
            int ye = ys + random.nextInt(height << 3);//y坐标结束

            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            //指定某种颜色
//            g.setColor(new Color(252, 145, 83));
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        imageCode.setCode(randomCode.toString());
        log.info("生成的图形验证码{}", imageCode.getCode());
        return imageCode;
    }

}
