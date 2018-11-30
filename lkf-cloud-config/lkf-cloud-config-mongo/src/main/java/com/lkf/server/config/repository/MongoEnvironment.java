package com.lkf.server.config.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * mongodb 中存储的配置信息
 *
 * @author 刘凯峰
 * @date 2018-10-30 09-31
 */
@Document
public class MongoEnvironment implements Serializable {
    /**
     * 应用名称
     */
    private String application;
    /**
     * 环境
     */
    private String profile;


    /**
     * 版本
     */
    private String label;
    /**
     * 优先级，数值越大，优先级越高
     */
    private int priority;

    /**
     * 配置项
     */
    private Map<String, String> conf;
    /**
     * 应用配置项
     */
    @Field("conf-ref")
    private List<String> confRef;

    public String getApplication() {
        return application;
    }

    public void setApplication( String application ) {
        this.application = application;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile( String profile ) {
        this.profile = profile;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority( int priority ) {
        this.priority = priority;
    }

    public Map<String, String> getConf() {
        return conf;
    }

    public void setConf( Map<String, String> conf ) {
        this.conf = conf;
    }

    public List<String> getConfRef() {
        return confRef;
    }

    public void setConfRef( List<String> confRef ) {
        this.confRef = confRef;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }
}
