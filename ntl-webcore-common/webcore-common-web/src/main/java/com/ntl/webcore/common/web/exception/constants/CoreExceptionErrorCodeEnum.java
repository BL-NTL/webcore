package com.ntl.webcore.common.web.exception.constants;

import com.ntl.webcore.common.web.exception.base.BaseErrorCode;

public enum CoreExceptionErrorCodeEnum implements BaseErrorCode {

    SYS_USER_ADD("USER_001", "用户入库异常");

    private String errorCode;

    private String errorMsg;

    CoreExceptionErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
    @Override
    public String getErrorMessage() {
        return errorMsg;
    }

}
