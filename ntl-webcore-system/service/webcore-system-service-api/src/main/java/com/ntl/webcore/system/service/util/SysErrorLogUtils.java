package com.ntl.webcore.system.service.util;

import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.common.web.exception.CommonRuntimeException;
import com.ntl.webcore.common.web.exception.base.BaseErrorCode;
import com.ntl.webcore.common.web.utils.spring.SpringUtils;
import com.ntl.webcore.system.common.model.entity.SysErrorLog;
import com.ntl.webcore.system.common.model.util.ShiroUtils;
import com.ntl.webcore.system.service.monitor.ISysErrorLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SysErrorLogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysErrorLogUtils.class);

    public static void insertErrorLog(String errorCode, String errorMessage, Exception exception){
        insertErrorLog(errorCode, errorMessage, exception, null);
    }

    public static void insertErrorLog(BaseErrorCode baseErrorCode, Exception exception){
        insertErrorLog(baseErrorCode.getErrorCode(), baseErrorCode.getErrorMessage(), exception, null);
    }

    /**
     * 保存错误日志
     * @param errorCode 错误代码
     * @param errorMessage 错误消息内容
     * @param exception 错误异常对象
     * @param requestIp
     */
    public static void insertErrorLog(String errorCode, String errorMessage, Exception exception, String requestIp){
        ISysErrorLogService errorLogService = SpringUtils.getBean(ISysErrorLogService.class);
        // error Type
        SysErrorLog errorLog = new SysErrorLog();
        errorLog.setErrorCode(errorCode);


        String msg = "";
        if(null != exception){
            errorLog.setErrorType(exception.getClass().toString());
            msg = exception.getMessage();
            if(StrUtils.isEmpty(msg) || exception.getCause() instanceof NullPointerException){
                StringWriter sw = new StringWriter();
                exception.printStackTrace(new PrintWriter(sw,true));
                msg = sw.toString();
            }
        }
        // error Message
        if(StrUtils.isEmpty(errorMessage)){
            errorMessage = msg;
        }else{
            errorMessage += "\n"+ msg;
        }
        errorLog.setErrorMessage(errorMessage);

        // request IP and operator
        try{
            if(StrUtils.isEmpty(requestIp)){
                requestIp = ShiroUtils.getIp();
            }
            errorLog.setRequestIp(requestIp);
            errorLog.setOperator(ShiroUtils.getLoginName());
        }catch(Exception e){
            //e.printStackTrace();
        }
        // service ID
        Environment environment = SpringUtils.getBean(Environment.class);
        errorLog.setServiceId(environment.getProperty("app.id"));
        //默认值
        errorLog.setStatus("0");
        try{
            errorLogService.insertSysErrorLog(errorLog);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 保存错误日志
     * @param e CommonRuntimeException
     */
    public static void insertErrorLogByException(CommonRuntimeException e, String requestIp){
        insertErrorLog(e.getErrorCode(),e.getErrorMessage(),e, requestIp);
    }


}
