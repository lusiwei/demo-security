package com.lusiwei.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lusiwei.browser.common.ResponseResult;
import com.lusiwei.dto.UserQueryCondition;
import com.lusiwei.exception.UserNotExistException;
import com.lusiwei.pojo.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 16:34
 * @Description:
 */
@RestController
public class UserController {
    @GetMapping("/user")
    @JsonView(User.SimpleView.class)
    public List<User> query(UserQueryCondition userQueryCondition){
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        ArrayList<User> objects = new ArrayList<>();
        objects.add(new User());
        objects.add(new User());
        objects.add(new User());
        return objects;
    }
    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.DetailView.class)
    public User getInfo(@PathVariable("id") Integer id){
        System.out.println("getInfo 方法执行了");
        User user = new User();
        user.setUsername("tom");
//        throw new UserNotExistException(id);
        return user;
    }
    @PostMapping("/user")
    @JsonView(User.SimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
        }
        user.setId(1);
        return user;
    }

    @GetMapping("/authentication/form")
    public ResponseResult loginProcess(){
        return new ResponseResult("登录成功");
    }

}
