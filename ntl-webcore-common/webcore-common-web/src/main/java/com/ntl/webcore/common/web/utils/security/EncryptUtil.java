package com.ntl.webcore.common.web.utils.security;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Calendar;

public class EncryptUtil {

    /**
     * 加密登录密码
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @param salt     MD5盐
     * @return
     */
    public static String encryptLoginPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    /**
     * @param str
     * @return 返回类型
     * @Title: 加密算法
     * @Description: TODO
     */
    public static final String encrypt(String str) {

        String string_in = "YaNb8cKd1eJfOgZhViUjRkBl3mMnDoEpTqSr5sGtPuLv2w7xAyXzWIHQ94C6F0#$_@!~`%^&*()-+=[]{}'|?;:/,<>.\"\\ ";
        String string_out = " @!~`%^&*()-+=[]{}'|?;:/,<>._$#AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789\"\\";
        String outpass = "";
        try {
            if (str != null) {
                int offset = 0;
                Calendar calendar = Calendar.getInstance();
                int ss = calendar.get(Calendar.SECOND);
                offset = ss % 94;
                if (offset > 0)
                    offset = offset - 1;
                outpass = string_in.substring(offset, offset + 1);
                string_in = string_in + string_in;
                string_in = string_in.substring(offset, offset + 95);
                outpass = outpass + translate(str, string_in, string_out);
                outpass = strToAscStr(outpass, "-");
                return outpass;
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param str
     * @return 返回类型
     * @Title: 解密算法
     */
    public static final String disencrypt(String str) {
        str = ascToStr(str, "-");
        String string_in = "YaNb8cKd1eJfOgZhViUjRkBl3mMnDoEpTqSr5sGtPuLv2w7xAyXzWIHQ94C6F0#$_@!~`%^&*()-+=[]{}'|?;:/,<>.\"\\ ";
        String string_out = " @!~`%^&*()-+=[]{}'|?;:/,<>._$#AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789\"\\";
        try {
            int offset = 0;
            char c = str.charAt(0);
            offset = string_in.indexOf(c);
            string_in = string_in + string_in;
            string_in = string_in.substring(offset, offset + 95);
            String s = str.substring(1);
            String inpass = translate(s, string_out, string_in);
            return inpass;
        } catch (Exception e) {
            return "";
        }
    }

    private static final String translate(String str, String string_in,
                                          String string_out) {

        char[] outc = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int j = string_in.indexOf(c);
            outc[i] = string_out.charAt(j);

        }
        String outs = new String(outc);

        return outs;
    }

    private static String strToAscStr(String str, String splitStr) {
        String retStr = "";
        if (null == str) {
            return "";
        }
        byte[] byteArray = str.getBytes();
        for (int i = 0; i < byteArray.length; i++) {
            retStr += Integer.toHexString(byteArray[i]) + splitStr;
        }
        if (retStr.length() > splitStr.length()) {
            return retStr.substring(0, retStr.length() - splitStr.length());
        } else {
            return retStr;
        }
    }

    private static String ascToStr(String str, String split) {
        String retStr = "";
        if (null == str) {
            return retStr;
        }
        String[] strArray = str.split(split);
        if (null != strArray && 0 < strArray.length) {
            for (int i = 0; i < strArray.length; i++) {
                retStr += (char) Integer.parseInt(strArray[i], 16);
            }
        }
        return retStr;
    }

    public static void main(String[] args) {
        System.out.println(encryptLoginPassword("suadmin", "123123", "123456"));

    }
}
