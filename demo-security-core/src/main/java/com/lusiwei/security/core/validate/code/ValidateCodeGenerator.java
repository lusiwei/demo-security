package com.lusiwei.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 13:14
 * @Description:
 */
public interface ValidateCodeGenerator {
    ValidateCode generateCode(HttpServletRequest request, HttpServletResponse response);
}
