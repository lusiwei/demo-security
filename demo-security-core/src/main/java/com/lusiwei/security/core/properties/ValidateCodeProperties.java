package com.lusiwei.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 15:11
 * @Description:
 */
@Setter
@Getter
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();
}
