package com.lusiwei.browser.handler;

import com.lusiwei.browser.common.ResponseResult;
import com.lusiwei.security.core.properties.LoginType;
import com.lusiwei.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 23:21
 * @Description:
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        if (securityProperties.getBrowser().getLoginType().equals(LoginType.JSON)) {
            String s = objectMapper.writeValueAsString(new ResponseResult(authentication));
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(s);
        }else {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
