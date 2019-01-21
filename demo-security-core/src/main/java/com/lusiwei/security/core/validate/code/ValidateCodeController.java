package com.lusiwei.security.core.validate.code;

import com.lusiwei.security.core.properties.ImageCodeProperties;
import com.lusiwei.security.core.properties.SecurityProperties;
import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 00:20
 * @Description:
 */
@RestController
public class ValidateCodeController {
    @Autowired
    private SecurityProperties securityProperties;
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);
        // 三个参数分别为宽、高、位数
        ImageCodeProperties image = securityProperties.getCode().getImage();
        int width= image.getWidth();
        int height= image.getHeight();
        int length= image.getLength();
        int expireIn = image.getExpireIn();
        width=ServletRequestUtils.getIntParameter(request,"width",width);
        height=ServletRequestUtils.getIntParameter(request, "height", height);
        GifCaptcha gifCaptcha = new GifCaptcha(width,height,length);

        // 设置字体
        gifCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));

        // 设置类型，纯数字、纯字母、字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        // 验证码存入session
        request.getSession().setAttribute("captcha", new ImageCode(gifCaptcha.text().toLowerCase(),expireIn));

        // 输出图片流
        gifCaptcha.out(response.getOutputStream());
    }

}
