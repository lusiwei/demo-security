package com.lusiwei.security.core.validate.code.sms;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 14:33
 * @Description:
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
