package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.CodeModel;
import com.kunlun.basedata.service.ICodeService;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class CodeService implements ICodeService {
    private Logger log = LogManager.getLogger();

    // 定义图片的width
    private static int width = 100;

    // 定义图片的height
    private static int height = 30;

    // 定义图片上显示验证码的个数
    private static int codeCount = 4;

    // x轴方向验证码间距
    private static int xx = 18;

    // 验证码字体大小
    private static int fontHeight = 25;

    // 验证码在y轴上的位置
    private static  int codeY = 24;

    private Random random = new Random();

    /**
     * 随机字符字典
     */
    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u' ,'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };

    @Override
    public CodeModel getAuthCode() throws Exception {
        Map<String,Object> map = generateCodeAndPic();

        // 字节数组输出流
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", ous);
        Base64Encoder encoder = new Base64Encoder();
        String code = encoder.encode(ous.toByteArray());

        // 设置验证码模型
        CodeModel codeModel = new CodeModel();
        codeModel.setId((int) (Math.random() + 100));
        codeModel.setCode(map.get("code").toString());
        codeModel.setBinary(code);
        codeModel.setCreateTime(new Date());

        log.info("Authentication Code：" + map.get("code"));
        return codeModel;
    }

    /**
     * 生成一个map集合
     * code为生成的验证码
     * codePic为生成的验证码BufferedImage对象
     * @return
     */
    public Map<String,Object> generateCodeAndPic() {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();

        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);

        // 设置字体。
        gd.setFont(font);

        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(54)]);

            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, i * xx + (i + 1) * 6, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }

        //存放验证码与验证码BufferedImage对象
        Map<String, Object> map  =new HashMap<String, Object>();
        map.put("code", randomCode);
        map.put("codePic", buffImg);
        return map;
    }

    /**
     * 获取4位随机数
     * @return
     */
    private String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            buffer.append(codeSequence[random.nextInt(codeSequence.length)]);
        }
        return buffer.toString();
    }

    /**
     * 获取随机数颜色
     * @return
     */
    private Color getRandomColor() {
        return new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255));
    }

    /**
     * 返回某颜色的反色
     * @param c
     * @return
     */
    private static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }
}
