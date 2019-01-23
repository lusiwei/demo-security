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
public class ImageCodeProperties extends SmsCodeProperties{
    private int width=67;
    private int height=23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
