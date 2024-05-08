package com.ntl.webcore.system.web.controller.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@Configuration
public class WebCoreMessageConfig {

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;
    @PostConstruct
    private void init() {
        messageSource.addBasenames("classpath:static/i18n/messages/webcore_system");
        messageSource.addBasenames("classpath:static/i18n/messages/webcore_common");
    }
}
