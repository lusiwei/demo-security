package com.lusiwei.security.core.social.qq.config;

import com.lusiwei.security.core.properties.QQProperties;
import com.lusiwei.security.core.properties.SecurityProperties;
import com.lusiwei.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 22:20
 * @Description:
 */
@Configuration
@ConditionalOnProperty(prefix = "demo.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),
                qq.getAppId(),qq.getAppSecret()
                );
    }
}
