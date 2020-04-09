package com.lkf.aop.controller;

import com.lkf.aop.jdkproxy.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * @date 2020-04-09 16:18
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserManager userManager;

    @RequestMapping(path = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerConsumeRecord(HttpEntity<String> httpEntity) throws Exception {
        userManager.delUser(httpEntity.getBody());
        return ResponseEntity.ok("结束");
    }
}
