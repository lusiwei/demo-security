package com.lusiwei.security.core.validate.code;

import com.lusiwei.security.core.properties.SecurityProperties;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 09:14
 * @Description:
 */
@Setter
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private Set<String> urls = new HashSet<>();
    private SecurityProperties securityProperties;
    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrls(), ",");
        urls.addAll(Arrays.asList(strings));
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ValidateCodeException {
        //session 中的验证码
        ImageCode imageCode = (ImageCode) request.getSession().getAttribute("captcha");
        //请求中的验证码
        String codeInRequest = request.getParameter("imageCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (imageCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCode.isExpired()) {
            CaptchaUtil.clear(request);
            throw new ValidateCodeException("验证码不存在");
        }
        if (!StringUtils.equalsIgnoreCase(codeInRequest, imageCode.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        CaptchaUtil.clear(request);
    }
}
