package com.ntl.webcore.common.web.utils;

import com.ntl.webcore.common.lang.constant.Constants;
import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.common.web.config.ApplicationProperties;
import com.ntl.webcore.common.web.utils.spring.SpringUtils;

public class ApplicationPropertiesUtil {

    public static String[] getJobWhiteArray(){
        String[] configJobWhiteArray = SpringUtils.getBean(ApplicationProperties.class)
                .getStringArrayFromEnvironment(Constants.JOB_WHITE_STR_ARRAY_PROPERTIES);
        if(null != configJobWhiteArray){
            return StrUtils.mergeArrays(Constants.JOB_WHITE_STR_ARRAY, configJobWhiteArray);
        }else{
            return Constants.JOB_WHITE_STR_ARRAY;
        }
    }

    public static String[] getJobErrorArray(){
        String[] configJobErrorArray = SpringUtils.getBean(ApplicationProperties.class)
                .getStringArrayFromEnvironment(Constants.JOB_ERROR_STR_ARRAY_PROPERTIES);
        if(null != configJobErrorArray){
            return StrUtils.mergeArrays(Constants.JOB_ERROR_STR_ARRAY, configJobErrorArray);
        }else{
            return Constants.JOB_ERROR_STR_ARRAY;
        }
    }
}
