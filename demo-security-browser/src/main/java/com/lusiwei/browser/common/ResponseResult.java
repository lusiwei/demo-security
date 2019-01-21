package com.lusiwei.browser.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 22:50
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult implements Serializable {
    private Object object;
}
