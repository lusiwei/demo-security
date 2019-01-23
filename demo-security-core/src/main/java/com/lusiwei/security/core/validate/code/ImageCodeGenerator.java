package com.lusiwei.security.core.validate.code;

import com.lusiwei.security.core.properties.ImageCodeProperties;
import com.lusiwei.security.core.properties.SecurityProperties;
import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 13:20
 * @Description:
 */
@Setter
@Getter
public class ImageCodeGenerator implements ValidateCodeGenerator {
    private SecurityProperties securityProperties;
    private GifCaptcha gifCaptcha;
    @Override
    public ValidateCode generateCode(HttpServletRequest request,HttpServletResponse response) {
        //设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);
        // 三个参数分别为宽、高、位数
        ImageCodeProperties image = securityProperties.getCode().getImage();
        int width = image.getWidth();
        int height = image.getHeight();
        int length = image.getLength();
        int expireIn = image.getExpireIn();
        width = ServletRequestUtils.getIntParameter(request, "width", width);
        height = ServletRequestUtils.getIntParameter(request, "height", height);
        GifCaptcha gifCaptcha = new GifCaptcha(width, height, length);

        // 设置字体
        gifCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));

        // 设置类型，纯数字、纯字母、字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        this.gifCaptcha=gifCaptcha;
        return new ImageCode(gifCaptcha.text().toLowerCase(), expireIn);
    }
    //输出到response.getOutputStream
    public void out(HttpServletResponse response){
        try {
            gifCaptcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidateCodeException("验证码输出失败");
        }
    }
}
