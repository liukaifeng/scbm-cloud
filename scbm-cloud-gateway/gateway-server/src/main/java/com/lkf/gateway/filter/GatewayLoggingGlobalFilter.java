package com.scbm.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求计时过滤器
 *
 * @author 刘凯峰
 * @date 2018-11-15 19-40
 */
@Component
public class GatewayLoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Log logger = LogFactory.getLog(GatewayLoggingGlobalFilter.class);
    private static final String START_TIME = "startTime";

    public GatewayLoggingGlobalFilter() {
        logger.info("Loaded GlobalFilter [Logging]");
    }

    @Override
    public Mono<Void> filter( ServerWebExchange exchange, GatewayFilterChain chain ) {
        String info = String.format("Method:{%s} Host:{%s} Path:{%s} " +
                        "Query:{%s} Original_Request_Url:{%s} Gateway_Request_Url:{%s}",
                exchange.getRequest().getMethod().name(),
                exchange.getRequest().getURI().getHost(),
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getQueryParams(),
                exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR),
                exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)
        );
        logger.info(info);

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            Long executeTime = (System.currentTimeMillis() - startTime);
            logger.info(exchange.getRequest().getURI().getRawPath() + " : " + executeTime + "ms");
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
