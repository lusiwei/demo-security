package com.lusiwei.security.core.social.qq.config;

import com.lusiwei.security.core.properties.QQProperties;
import com.lusiwei.security.core.properties.SecurityProperties;
import com.lusiwei.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 22:20
 * @Description:
 */
@Configuration
@ConditionalOnProperty(prefix = "demo.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),
                qq.getAppId(),qq.getAppSecret()
                );
    }
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        if (connectionSignUp != null) {
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return usersConnectionRepository;
    }
}
