package com.ntl.webcore.common.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationProperties {

    @Autowired
    private Environment environment;

    public String getEnvironmentValue(String environmentKey) {
        return environment.getProperty(environmentKey);
    }

    public String[] getStringArrayFromEnvironment(String environmentArrayKey) {
        String value = environment.getProperty(environmentArrayKey);
        if (value != null) {
            return value.split(",");
        }
        return null; // 或者你可以返回一个空数组 new String[0]
    }

}
