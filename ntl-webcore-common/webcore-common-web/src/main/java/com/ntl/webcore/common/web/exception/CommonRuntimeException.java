package com.ntl.webcore.common.web.exception;

/**
 *
 */
public class CommonRuntimeException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    /**
     * errorMessage 构造方法
     */
    public CommonRuntimeException(){
        super();
    }

    /**
     * errorMessage 构造方法
     * @param errorMessage 错误消息
     */
    public CommonRuntimeException(String errorMessage){
        super();
        this.errorMessage = errorMessage;
    }

    /**
     * 错误编码、错误消息构造方法
     * @param errorCode
     * @param errorMessage
     */
    public CommonRuntimeException(String errorCode, String errorMessage){
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public CommonRuntimeException(String errorCode, String errorMessage, Throwable exception){
        super(exception);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
