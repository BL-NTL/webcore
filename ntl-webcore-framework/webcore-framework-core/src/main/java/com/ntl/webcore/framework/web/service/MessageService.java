package com.ntl.webcore.framework.web.service;

import com.ntl.webcore.common.web.utils.spring.ServletUtils;
import com.ntl.webcore.framework.config.i18n.I18nProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Service("messageService")
public class MessageService {

    private final I18nProperties i18nProperties;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    public MessageService(I18nProperties i18nProperties) {
        this.i18nProperties = i18nProperties;
    }

    public List<String> getModuleNames(){
        return i18nProperties.getModuleNames();
    }

    public String getLanguageCode(){
        // 获取当前Locale
        Locale currentLocale = localeResolver.resolveLocale(ServletUtils.getRequest());
        return currentLocale.toString();
    }

}
