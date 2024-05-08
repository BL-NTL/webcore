package com.ntl.webcore.quartz.task;

import com.ntl.webcore.common.lang.string.StrUtils;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * 
 */
@Component("webcoreTask")
public class WebcoreTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StrUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void jobParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void jobNoParams()
    {
        System.out.println("执行无参方法");
    }
}
