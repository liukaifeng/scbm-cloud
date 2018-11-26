package com.lkf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 路由动态注入
 *
 * @author 刘凯峰
 * @date 2018-11-22 15-36
 */
@Data
@Component
@ConfigurationProperties("route")
public class DynamicRouteProperties {
    private String routeId;
    private String serviceId;
    private String routePath;

}
