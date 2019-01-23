package com.lusiwei.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 15:11
 * @Description:
 */
public interface ValidateCodeProcessor {
    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;

    @SuppressWarnings("unchecked")
    void validate(ServletWebRequest request);
}
