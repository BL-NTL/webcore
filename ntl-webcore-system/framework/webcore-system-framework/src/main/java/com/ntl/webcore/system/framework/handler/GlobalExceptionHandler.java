package com.ntl.webcore.system.framework.handler;

import com.ntl.webcore.common.web.exception.DemoModeException;
import com.ntl.webcore.common.web.exception.ServiceException;
import com.ntl.webcore.common.web.exception.constants.WebCoreExceptionConstant;
import com.ntl.webcore.common.web.model.AjaxResult;
import com.ntl.webcore.common.web.utils.MessageUtils;
import com.ntl.webcore.common.web.utils.security.PermissionUtils;
import com.ntl.webcore.common.web.utils.spring.ServletUtils;
import com.ntl.webcore.system.service.util.SysErrorLogUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常（ajax请求返回json，redirect请求跳转页面）
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_AUTHORIZATION_EXCEPTION, requestURI,
                e, request.getRemoteAddr());
        if (ServletUtils.isAjaxRequest(request))
        {
            return AjaxResult.error(PermissionUtils.getMsg(e.getMessage()));
        }
        else
        {
            return new ModelAndView("error/unauth");
        }
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION,
                requestURI,e, request.getRemoteAddr());
        return AjaxResult.error(MessageUtils.message("exception.core.errorCode.label")
                + WebCoreExceptionConstant.GLOBAL_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION + ","
                + MessageUtils.message("exception.core.errorCode.message"));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_RUNTIME_EXCEPTION,
                requestURI,e, request.getRemoteAddr());
        return AjaxResult.error(MessageUtils.message("exception.core.errorCode.label")
                    + WebCoreExceptionConstant.GLOBAL_RUNTIME_EXCEPTION + ","
                + MessageUtils.message("exception.core.errorCode.message"));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_EXCEPTION,
                requestURI, e, request.getRemoteAddr());
        return AjaxResult.error(MessageUtils.message("exception.core.errorCode.label") + WebCoreExceptionConstant.GLOBAL_EXCEPTION + ","
                + MessageUtils.message("exception.core.errorCode.message"));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Object handleServiceException(ServiceException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_SERVICE_EXCEPTION,
                request.getRequestURI(),e, request.getRemoteAddr());
        if (ServletUtils.isAjaxRequest(request))
        {
            return AjaxResult.error(MessageUtils.message("exception.core.errorCode.label")
                    + WebCoreExceptionConstant.GLOBAL_SERVICE_EXCEPTION + ","
                    + MessageUtils.message("exception.core.errorCode.message"));
        }
        else
        {
            return new ModelAndView("error/service", "errorMessage",
                    MessageUtils.message("exception.core.errorCode.label")
                            + WebCoreExceptionConstant.GLOBAL_SERVICE_EXCEPTION + ","
                            + MessageUtils.message("exception.core.errorCode.message"));
        }
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_BIND_EXCEPTION,
                message, e, request.getRemoteAddr());
        return AjaxResult.error(message);
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult handleDemoModeException(DemoModeException e, HttpServletRequest request)
    {
        SysErrorLogUtils.insertErrorLog(WebCoreExceptionConstant.GLOBAL_DEMO_MODE_EXCEPTION,
                "演示模式，不允许操作", e, request.getRemoteAddr());
        return AjaxResult.error("演示模式，不允许操作");
    }
}
