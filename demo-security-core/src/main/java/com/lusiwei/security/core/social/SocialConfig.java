package com.lusiwei.security.core.social;

import com.lusiwei.security.core.properties.SecurityProperties;
import com.lusiwei.security.core.social.security.MySpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @Author: lusiwei
 * @Date: 2019/1/21 21:58
 * @Description:
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Bean
    public SpringSocialConfigurer mySocialSecurityConfig() {
        MySpringSocialConfigurer configurer;
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        configurer = new MySpringSocialConfigurer(filterProcessUrl);
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }
    @Bean
    public ProviderSignInUtils providerSignInUtils(){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
