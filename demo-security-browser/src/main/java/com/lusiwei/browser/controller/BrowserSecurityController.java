package com.lusiwei.browser.controller;

import com.lusiwei.browser.common.ResponseResult;
import com.lusiwei.browser.supports.SocialUserInfo;
import com.lusiwei.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 22:41
 * @Description:
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 缓存上次请求
     */
    private RequestCache requestCache = new HttpSessionRequestCache();
    /**
     * 重定向带上request,response
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是：" + redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new ResponseResult("访问的服务需要身份认证，请引导用户到登录页");
    }
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        socialUserInfo.setNickname(connectionFromSession.getDisplayName());
        socialUserInfo.setHeadimg(connectionFromSession.getImageUrl());
        logger.info("获取到的providerUserId为"+connectionFromSession.getKey().getProviderUserId());
        return socialUserInfo;
    }

}
