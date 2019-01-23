package com.lusiwei.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 15:09
 * @Description:
 */
@Setter
@Getter
public class SmsCodeProperties {
    private int length=6;
    private int expireIn=60;
    private String urls;
}
