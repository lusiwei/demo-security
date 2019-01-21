package com.lusiwei.security.core.social.qq.connect;

import com.lusiwei.security.core.social.qq.api.QQ;
import com.lusiwei.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 21:32
 * @Description:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appid;
    private static final String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";
    public QQServiceProvider(String appid,String appSecret) {
        super(new OAuth2Template(appid,appSecret,URL_AUTHORIZE,URL_ACCESSTOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appid);
    }
}
