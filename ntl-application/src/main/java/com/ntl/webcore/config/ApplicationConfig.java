package com.ntl.webcore.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 程序注解配置
 *
 * 
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 多个已逗号分隔，例如 "com.ntl","com.xxx"
@ComponentScan(basePackages = {"com.ntl"})
// 指定要扫描的Mapper类的包的路径,多个以逗号分隔 例如"com.ntl.webcore.**.mapper","com.xxx.**.mapper"
@MapperScan({"com.ntl.webcore.**.mapper"})
public class ApplicationConfig
{

}
