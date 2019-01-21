package com.lusiwei.security.core.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 22:59
 * @Description:
 */
@ConfigurationProperties(prefix = "demo.security")
@Getter
public class SecurityProperties {
    private BrowserProperties browser=new BrowserProperties();
    private ValidateCodeProperties code=new ValidateCodeProperties();
}
