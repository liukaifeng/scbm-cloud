package com.lkf.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-23 13-42
 */
//@Component
public class ResourceLoadBalancerClientFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(ResourceLoadBalancerClientFilter.class);
    public static final int LOAD_BALANCER_CLIENT_FILTER_ORDER = LoadBalancerClientFilter.LOAD_BALANCER_CLIENT_FILTER_ORDER - 1;

    protected final LoadBalancerClient loadBalancer;

    public ResourceLoadBalancerClientFilter( LoadBalancerClient loadBalancer ) {
        this.loadBalancer = loadBalancer;
    }

    @Override
    public int getOrder() {
        return LOAD_BALANCER_CLIENT_FILTER_ORDER;
    }

    @Override
    public Mono<Void> filter( ServerWebExchange exchange, GatewayFilterChain chain ) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        String schemePrefix = exchange.getAttribute(GATEWAY_SCHEME_PREFIX_ATTR);
        if (Objects.isNull(url) || (!"lb".equals(url.getScheme()) && !"lb".equals(schemePrefix))) {
            return chain.filter(exchange);
        }
        //记录原始请求地址（请求网关的地址）
        addOriginalRequestUrl(exchange, url);
        log.trace("ResourceLoadBalancerClientFilter url before: " + url);
        //负载均衡选择一个服务实例
        final ServiceInstance instance = choose(exchange);

        if (instance == null) {
            throw new NotFoundException("Unable to find instance for " + url.getHost());
        }

        URI uri = exchange.getRequest().getURI();

        // if the `lb:<scheme>` mechanism was used, use `<scheme>` as the default,
        // if the loadbalancer doesn't provide one.
        String overrideScheme = null;
        if (schemePrefix != null) {
            overrideScheme = url.getScheme();
        }

        URI requestUrl = loadBalancer.reconstructURI(new ResourceLoadBalancerClientFilter.DelegatingServiceInstance(instance, overrideScheme), uri);

        log.trace("ResourceLoadBalancerClientFilter url chosen: " + requestUrl);
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, requestUrl);
        return chain.filter(exchange);
    }

    protected ServiceInstance choose( ServerWebExchange exchange ) {
        return loadBalancer.choose(((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost());
    }

    class DelegatingServiceInstance implements ServiceInstance {
        final ServiceInstance delegate;
        private String overrideScheme;

        DelegatingServiceInstance( ServiceInstance delegate, String overrideScheme ) {
            this.delegate = delegate;
            this.overrideScheme = overrideScheme;
        }

        @Override
        public String getServiceId() {
            return delegate.getServiceId();
        }

        @Override
        public String getHost() {
            return delegate.getHost();
        }

        @Override
        public int getPort() {
            return delegate.getPort();
        }

        @Override
        public boolean isSecure() {
            return delegate.isSecure();
        }

        @Override
        public URI getUri() {
            return delegate.getUri();
        }

        @Override
        public Map<String, String> getMetadata() {
            return delegate.getMetadata();
        }

        @Override
        public String getScheme() {
            String scheme = delegate.getScheme();
            if (scheme != null) {
                return scheme;
            }
            return this.overrideScheme;
        }

    }
}
