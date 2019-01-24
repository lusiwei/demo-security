package com.lusiwei.security.core.social.wechat.api;

/**
 * @Author: lusiwei
 * @Date: 2019/1/23 14:12
 * @Description:
 */
public interface Wechat {
    WechatUserInfo getUserInfo(String openId);
}
