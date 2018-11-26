package com.lkf.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-26 16-53
 */
@Component
public class RequestRedirectGatewayFilter implements GlobalFilter, Ordered {
    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter( ServerWebExchange exchange, GatewayFilterChain chain ) {
        Object uriObj = exchange.getAttributes().get(GATEWAY_REQUEST_URL_ATTR);
        if (uriObj != null) {
            URI uri = (URI) uriObj;
            uri = this.upgradeConnection(uri, "http");
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, uri);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return LoadBalancerClientFilter.LOAD_BALANCER_CLIENT_FILTER_ORDER + 1;
    }

    private URI upgradeConnection( URI uri, String scheme ) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(uri).scheme(scheme);
        if (uri.getRawQuery() != null) {
            // When building the URI, UriComponentsBuilder verify the allowed characters and does not
            // support the '+' so we replace it for its equivalent '%20'.
            // See issue https://jira.spring.io/browse/SPR-10172
            uriComponentsBuilder.replaceQuery(uri.getRawQuery().replace("+", "%20"));
        }
        return uriComponentsBuilder.build(true).toUri();
    }
}
