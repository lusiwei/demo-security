package com.lusiwei.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 22:15
 * @Description:
 */
@Setter
@Getter
public class QQProperties extends SocialProperties {
    private String providerId="qq";
}
