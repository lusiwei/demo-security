package com.lusiwei.exception;

import lombok.Getter;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 19:57
 * @Description:
 */
@Getter
public class UserNotExistException extends RuntimeException{
    private Integer id;
    public UserNotExistException(Integer id) {
        super("用户名不存在");
        this.id=id;
    }
}
