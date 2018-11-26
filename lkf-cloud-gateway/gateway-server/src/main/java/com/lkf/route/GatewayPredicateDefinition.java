package com.lkf.route;

/**
 * 路由断言定义
 *
 * @author 刘凯峰
 * @date 2018-11-26 20-03
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
