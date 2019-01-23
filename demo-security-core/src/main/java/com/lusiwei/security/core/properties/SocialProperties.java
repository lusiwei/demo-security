package com.lusiwei.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 22:16
 * @Description:
 */
@Getter
@Setter
public class SocialProperties {
    private String filterProcessUrl = "/auth";
    private QQProperties qq=new QQProperties();
}
