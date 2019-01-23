package com.lusiwei.security.core.validate.code;

import com.lusiwei.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 00:20
 * @Description:
 */
@RestController
public class ValidateCodeController {
    @Autowired
    private ImageCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response){
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generateCode(request, response);
        request.getSession().setAttribute("captcha_image",imageCode);
        imageCodeGenerator.out(response);
    }
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generateCode(request, response);
        request.getSession().setAttribute("captcha_sms",smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }
}
