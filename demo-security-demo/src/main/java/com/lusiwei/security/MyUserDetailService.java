package com.lusiwei.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;


/**
 * @Author: lusiwei
 * @Date: 2019/1/20 21:51
 * @Description:
 */
@Component
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        logger.info("username is:"+username);
        logger.info("password is: "+password);
        return new User(username, password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        logger.info("username is:"+userId);
        logger.info("password is: "+password);
        return new SocialUser(userId, password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
