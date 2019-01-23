package com.lusiwei.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lusiwei.browser.common.ResponseResult;
import com.lusiwei.dto.UserQueryCondition;
import com.lusiwei.pojo.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
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

    @PostMapping("/user/register")
    public void register(User user, HttpServletRequest request){
        //注册用户
        String userId=user.getUsername();
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
    }
    @GetMapping("/user/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }

}
