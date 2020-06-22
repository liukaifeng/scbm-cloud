package com.scbm.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 计算平台网关配置属性
 *
 * @author 刘凯峰
 * @date 2018-11-29 13-13
 */
@Data
@ConfigurationProperties(prefix = "cmp.gateway.route")
public class CmpGatewayProperties {
    private String collection;
}
