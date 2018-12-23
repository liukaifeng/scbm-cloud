package com.lkf.filter;

import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.Optional;

/**
 * to do desc
 *
 * @author kaifeng
 * @date 2018/11/25
 */
public class RequestBodyToRequestUriGatewayFilterFactory extends
        AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    public RequestBodyToRequestUriGatewayFilterFactory() {
        super(NameConfig.class);
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange, NameConfig config) {
        return Optional.empty();
    }
}
