package com.scbm.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * todo
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-24 15:35
 */
@Data
public class User {

    private Long id;

    @NotBlank(message = "用户名不能为空！")
    @Length(min = 4, max = 30, message = "用户名只能在4~30位之间！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    @Length(min = 8, message = "密码不能小于8位！")
    private String password;

}
