package com.ntl.webcore.common.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 全局配置类
 * 
 * 
 */
@Component
@ConfigurationProperties(prefix = "app")
public class WebcoreConfig
{
    /** 项目名称 */
    private static String name;
    /**
     * 软件名称
     */
    private static String softName;

    /**
     * 客户平台名称
     */
    private static String platformName;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 基础路径 */

    private static String windowsBasePath;

    private static String linuxBasePath;


    /** 获取地址开关 */
    private static boolean addressEnabled;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        WebcoreConfig.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        WebcoreConfig.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        WebcoreConfig.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        WebcoreConfig.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return getBasePath() + File.separator + "uploadPath";
    }

    public static String getBasePath() {
        // 根据操作系统选择路径
        return isWindows() ? windowsBasePath : linuxBasePath;
    }


    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static String getWindowsBasePath() {
        return windowsBasePath;
    }

    @Value("${app.basePath.windows}")
    public void setWindowsBasePath(String windowsBasePath) {
        WebcoreConfig.windowsBasePath = windowsBasePath;
    }

    public static String getLinuxBasePath() {
        return linuxBasePath;
    }

    @Value("${app.basePath.linux}")
    public void setLinuxBasePath(String linuxBasePath) {
        WebcoreConfig.linuxBasePath = linuxBasePath;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        WebcoreConfig.addressEnabled = addressEnabled;
    }

    public static String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        WebcoreConfig.softName = softName;
    }

    public static String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        WebcoreConfig.platformName = platformName;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getProfile() + File.separator + "import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + File.separator + "avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + File.separator + "download" + File.separator;
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + File.separator +"upload";
    }
}
