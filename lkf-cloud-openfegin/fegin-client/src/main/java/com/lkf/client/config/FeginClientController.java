package com.lkf.client.config;

import com.lkf.server.fegin.UserControllerFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-30 11-15
 */
@RestController
@RequestMapping(value = "/api/fegin")
public class FeginClientController {
    private final UserControllerFegin userControllerFegin;

    @Autowired
    public FeginClientController( UserControllerFegin userControllerFegin ) {
        this.userControllerFegin = userControllerFegin;
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok(userControllerFegin.getUser("你好啊。。。"));
    }

}
