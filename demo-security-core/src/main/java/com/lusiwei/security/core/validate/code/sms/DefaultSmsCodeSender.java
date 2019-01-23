package com.lusiwei.security.core.validate.code.sms;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 14:34
 * @Description:
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送验证码"+code);
    }
}
