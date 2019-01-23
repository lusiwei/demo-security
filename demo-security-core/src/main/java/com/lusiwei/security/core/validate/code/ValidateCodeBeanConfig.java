package com.lusiwei.security.core.validate.code;

import com.lusiwei.security.core.properties.SecurityProperties;
import com.lusiwei.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.lusiwei.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 13:31
 * @Description:
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator validateCodeGenerator = new ImageCodeGenerator();
        validateCodeGenerator.setSecurityProperties(securityProperties);
        return validateCodeGenerator;
    }
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
