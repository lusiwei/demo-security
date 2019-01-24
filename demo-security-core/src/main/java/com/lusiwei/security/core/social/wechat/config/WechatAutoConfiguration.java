package com.lusiwei.security.core.social.wechat.config;

import com.lusiwei.security.core.properties.SecurityProperties;
import com.lusiwei.security.core.properties.WechatProperties;
import com.lusiwei.security.core.social.wechat.connect.WechatConnectionFactory;
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

@Configuration
@ConditionalOnProperty(prefix = "demo.security.social.wechat", name = "app-id")
public class WechatAutoConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	@Autowired
	private DataSource dataSource;
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WechatProperties weixinConfig = securityProperties.getSocial().getWechat();
		return new WechatConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
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
