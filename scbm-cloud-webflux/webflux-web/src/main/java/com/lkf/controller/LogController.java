package com.scbm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-01 15:56
 * @description TODO
 */
@Slf4j
public class LogController {
    public static void main(String... args) {
        for (int i = 0; i < 1000; i++) {
            log.error("Something else is wrong here");
            log.info("something else is info here");
        }
    }
}
