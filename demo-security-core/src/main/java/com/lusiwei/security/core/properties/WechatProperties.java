package com.lusiwei.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: lusiwei
 * @Date: 2019/1/23 14:07
 * @Description:
 */
public class WechatProperties extends SocialProperties {
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "wechat";

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
