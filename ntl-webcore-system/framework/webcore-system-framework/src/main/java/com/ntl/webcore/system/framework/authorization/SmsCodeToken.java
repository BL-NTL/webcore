package com.ntl.webcore.system.framework.authorization;

import org.apache.shiro.authc.AuthenticationToken;

public class SmsCodeToken implements AuthenticationToken {

    private final String phoneNumber;
    private final String smsCode;

    public SmsCodeToken(String phoneNumber, String smsCode) {
        this.phoneNumber = phoneNumber;
        this.smsCode = smsCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSmsCode() {
        return smsCode;
    }

    @Override
    public Object getPrincipal() {
        return phoneNumber + smsCode;
    }

    @Override
    public Object getCredentials() {
        return phoneNumber;
    }
}
