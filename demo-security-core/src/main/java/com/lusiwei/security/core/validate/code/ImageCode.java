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
public class ImageCode {
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
