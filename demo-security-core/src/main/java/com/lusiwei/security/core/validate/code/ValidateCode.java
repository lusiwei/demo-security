package com.lusiwei.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: lusiwei
 * @Date: 2019/1/22 14:06
 * @Description:
 */
@Getter
@Setter
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;
    //直接指定多少时间后过期
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    //直接指定过期时间
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code=code;
        this.expireTime=expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
