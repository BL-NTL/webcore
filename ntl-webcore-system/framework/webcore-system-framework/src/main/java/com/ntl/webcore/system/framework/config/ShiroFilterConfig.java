package com.ntl.webcore.system.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限过滤配置
 * @author Byron.Liu
 */
@Component
@ConfigurationProperties(prefix = "shiro-filter")
public class ShiroFilterConfig {

    private List<String> anonList;

    public List<String> getAnonList() {
        return anonList;
    }

    public void setAnonList(List<String> anonList) {
        this.anonList = anonList;
    }
}
