package com.lkf.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 鉴权认证过滤器
 * 通过继承AbstractGatewayFilterFactory实现
 *
 * @author 刘凯峰
 * @date 2018-11-15 15-14
 */
@Component
public class AuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizeGatewayFilterFactory.Config> {

    private static final Log logger = LogFactory.getLog(AuthorizeGatewayFilterFactory.class);
    //登录令牌
    private static final String AUTHORIZE_TOKEN = "token";

    //redis字符串模板工具
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public AuthorizeGatewayFilterFactory( StringRedisTemplate stringRedisTemplate ) {
        super(Config.class);
        logger.info("Loaded GatewayFilterFactory [Authorize]");
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply( AuthorizeGatewayFilterFactory.Config config ) {
        return ( exchange, chain ) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            //请求对象
            ServerHttpRequest request = exchange.getRequest();
            //获取请求头
            HttpHeaders headers = request.getHeaders();
            //从请求头获取认证的Token
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            //如果请求头中找不到Token，从请求参数中获取
            if (StringUtils.isEmpty(token)) {
                token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            }
            //获取响应对象
            ServerHttpResponse response = exchange.getResponse();
            //如果令牌和令牌ID都为空
            String authToken = stringRedisTemplate.opsForValue().get(token);
            //令牌不存在或令牌不正确，返回401
            if (Strings.isBlank(authToken) || !authToken.equals(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 是否开启认证的开关
     *
     * @author 刘凯峰
     * @date 2018/11/15 15:22
     */
    public static class Config {

        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled( boolean enabled ) {
            this.enabled = enabled;
        }
    }
}
