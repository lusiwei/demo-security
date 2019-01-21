package com.lusiwei.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 16:35
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public interface SimpleView { }

    public interface DetailView extends SimpleView {
    }

    @JsonView(SimpleView.class)
    private Integer id;
    @JsonView(SimpleView.class)
    private String username;
    @JsonView(DetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;
    @JsonView(SimpleView.class)
    private String phone;
}
