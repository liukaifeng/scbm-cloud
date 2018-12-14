package com.lkf.server.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-30 10-22
 */
@FeignClient(value = "fegin-server",url = "${server.url}")
@RequestMapping(value = "/api/user")
public interface UserControllerFegin {
    @RequestMapping(path = "/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUser( @PathVariable("userName") String userName );
}
