package com.lusiwei.security.core.social.qq.connect;

import com.lusiwei.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 21:55
 * @Description:
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>  {
    public QQConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
