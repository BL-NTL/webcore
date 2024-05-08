package com.ntl.webcore.system.common.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntl.webcore.system.common.model.base.BaseEntity;
import com.ntl.webcore.system.common.model.util.poi.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 错误日志 table sys_error_log
 *
 * @author ntl
 * @date 2023-06-20
 */
public class SysErrorLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 错误代码 */
    @Excel(name = "错误代码")
    private String errorCode;

    /** 异常类型 */
    @Excel(name = "异常类型")
    private String errorType;

    /** 错误日志 */
    @Excel(name = "错误日志")
    private String errorMessage;

    /** 阅读状态（0=未读&1=已读） */
    @Excel(name = "阅读状态", readConverterExp = "0==未读&1=已读")
    private String status;

    /** 请求IP */
    @Excel(name = "异常服务器IP")
    private String requestIp;

    /** 异常服务器IP */
    @Excel(name = "异常服务器IP")
    private String serverIp;

    /** 异常服务器服务ID */
    @Excel(name = "异常服务器服务ID")
    private String serviceId;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 日志时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日志时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logTime;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    public String getErrorType()
    {
        return errorType;
    }
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
    public void setServerIp(String serverIp)
    {
        this.serverIp = serverIp;
    }

    public String getServerIp()
    {
        return serverIp;
    }
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceId()
    {
        return serviceId;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }
    public void setLogTime(Date logTime)
    {
        this.logTime = logTime;
    }

    public Date getLogTime()
    {
        return logTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("errorCode", getErrorCode())
                .append("errorType", getErrorType())
                .append("errorMessage", getErrorMessage())
                .append("status", getStatus())
                .append("requestIp", getRequestIp())
                .append("serverIp", getServerIp())
                .append("serviceId", getServiceId())
                .append("operator", getOperator())
                .append("logTime", getLogTime())
                .append("remark", getRemark())
                .toString();
    }
}

