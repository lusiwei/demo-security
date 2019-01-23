package com.lusiwei.security.core.validate.code.sms;

import com.lusiwei.security.core.validate.code.ValidateCode;
import com.lusiwei.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 15:27
 * @Description:
 */
@Component("imageCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {

    }
}
