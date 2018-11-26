package com.lkf.route;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器定义模型
 *
 * @author 刘凯峰
 * @date 2018-11-26 20-04
 */

public class GatewayFilterDefinition {

    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs( Map<String, String> args ) {
        this.args = args;
    }
}
