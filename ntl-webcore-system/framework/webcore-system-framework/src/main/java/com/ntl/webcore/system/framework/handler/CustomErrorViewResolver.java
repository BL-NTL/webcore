package com.ntl.webcore.system.framework.handler;

import com.ntl.webcore.system.service.util.SysErrorLogUtils;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.ntl.webcore.common.web.exception.constants.WebCoreExceptionConstant.HTTP_404;
import static com.ntl.webcore.common.web.exception.constants.WebCoreExceptionConstant.HTTP_500;

@Component
public class CustomErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        // 将错误信息添加到ModelAndView中
        ModelAndView modelAndView = new ModelAndView();
        String errorMsg = "";
        if(null != model){
            errorMsg = model.toString();
            modelAndView.addObject("errorMessage", errorMsg);
        }
        if(HttpStatus.NOT_FOUND.equals(status)){
            SysErrorLogUtils.insertErrorLog(HTTP_404, errorMsg, null, request.getRemoteAddr());
            modelAndView.setViewName("error/404");
        }else{
            SysErrorLogUtils.insertErrorLog(HTTP_500, errorMsg, null, request.getRemoteAddr());
            modelAndView.setViewName("error/500");
        }

        return modelAndView;
    }
}

