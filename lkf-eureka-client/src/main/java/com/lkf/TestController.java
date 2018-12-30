package com.lkf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * to do desc
 *
 * @author kaifeng
 * @date 2018/12/30
 */
@RequestMapping(value = "/lkf")
@RefreshScope
@RestController
public class TestController {
    @Value("${refresh.scope.value}")
    private String refreshScope;

    @GetMapping(value = "/test")
    public String testMethod() {
        return refreshScope;
    }
}
