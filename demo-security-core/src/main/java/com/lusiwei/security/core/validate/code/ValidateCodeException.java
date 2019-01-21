package com.lusiwei.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 11:27
 * @Description:
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
