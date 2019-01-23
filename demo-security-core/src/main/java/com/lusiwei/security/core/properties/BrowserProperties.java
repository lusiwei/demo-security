package com.lusiwei.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 23:00
 * @Description:
 */
@Setter
@Getter
public class BrowserProperties {
    private String loginPage="/myLogin.html";
    private String signUpUrl="/myRegister.html";
    private LoginType loginType=LoginType.JSON;
    private int rememberMeSeconds=3600;
}
