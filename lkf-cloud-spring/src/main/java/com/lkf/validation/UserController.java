package com.lkf.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

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
 * @date 2020-04-24 15:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public String register(@Valid User user) {
        return "注册成功！";
    }

    @PostMapping
    public String register(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            String errMsg = result.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("|"));
            return errMsg;
        }
        return "注册成功！";
    }

    @Autowired
    private ValidatorImpl validator;

    @PostMapping
    public String register2(User user){
        ValidationResult validationResult = validator.validate(user);
        if (validationResult.isHasErrors()){
            return validationResult.getErrMsg();
        }
        return "注册成功！";
    }
}
