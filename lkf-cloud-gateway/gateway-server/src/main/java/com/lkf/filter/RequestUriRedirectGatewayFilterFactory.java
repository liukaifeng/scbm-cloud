package com.lkf.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * todo 一句话描述该类的用途
 *
 * @author 刘凯峰
 * @date 2018-11-26 15-30
 */
//@Component
public class RequestUriRedirectGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestUriRedirectGatewayFilterFactory.Config> {

    private static final Log logger = LogFactory.getLog(RequestUriRedirectGatewayFilterFactory.class);

    public static final String ENABLE_KEY = "enabled";

    public RequestUriRedirectGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(ENABLE_KEY);
    }

    @Override
    public GatewayFilter apply( Config config ) {
        return ( exchange, chain ) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            Object uriObj = exchange.getAttributes().get(GATEWAY_REQUEST_URL_ATTR);
            if (uriObj != null) {
                URI uri = (URI) uriObj;
                uri = this.upgradeConnection(uri, "http");
                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, uri);
            }
            return chain.filter(exchange);
        };
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





