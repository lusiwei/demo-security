package com.lusiwei.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: lusiwei
 * @Date: 2019/1/21 00:17
 * @Description:
 */
@Getter
@Setter
public class ImageCode extends ValidateCode{
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(String code, int expireTime) {
        super(code,expireTime);
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
    public ImageCode(String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.code=code;
        this.expireTime=expireTime;
    }

}
