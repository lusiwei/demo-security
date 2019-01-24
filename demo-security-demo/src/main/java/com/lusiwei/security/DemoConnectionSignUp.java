package com.lusiwei.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author: lusiwei
 * @Date: 2019/1/23 10:30
 * @Description:
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
