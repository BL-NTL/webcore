package com.ntl.webcore.generator.tool.database.utils;

import com.ntl.webcore.generator.tool.database.bean.DBTable;

public enum TableEnum implements DBTable {

    SYS_USER("SYS_USER","1.用户表",
                    "user_id,用户ID,varchar,Y,32,N,,,|" +
                    "dept_id,部门ID,varchar,N,32,N,,,|" +
                    "login_name,登录账号,varchar,N,30,N,,,|" +
                    "user_name,用户昵称,varchar,N,30,Y,空,,|" +
                    "user_type,用户类型,varchar,N,2,N,00,,|" +
                    "email,用户邮箱,varchar,N,50,Y,空,,|" +
                    "phonenumber,手机号码,varchar,N,30,Y,空,,|" +
                    "sex,用户性别（0=男&1=女&2=未知）,char,N,1,N,0,,|" +
                    "avatar,头像路径,varchar,N,100,Y,空,,|" +
                    "password,密码,varchar,N,50,N,,,|" +
                    "salt,加密盐,varchar,N,20,N,,,|" +
                    "status,帐号状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "del_flag,删除标志（0=代表存在&2=代表删除）,char,N,1,N,0,,|" +
                    "login_ip,最后登录IP,varchar,N,128,Y,空,,|" +
                    "login_date,最后登录时间,datetime,N,,Y,,,|" +
                    "pwd_update_date,密码最后更新时间,datetime,N,,Y,,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_DEPT("SYS_DEPT","2.部门表",
            "dept_id,部门主键,varchar,Y,32,N,,,|" +
                    "parent_id,父级部门ID,varchar,N,32,N,0,,|" +
                    "ancestors,祖级列表,varchar,N,320,Y,空,,|" +
                    "dept_name,部门名称,varchar,N,50,N,,,|" +
                    "order_num,显示顺序,int,N,4,N,0,,|" +
                    "leader,负责人,varchar,N,20,Y,空,,|" +
                    "phone,联系电话,varchar,N,25,Y,空,,|" +
                    "email,邮箱,varchar,N,50,Y,空,,|" +
                    "status,部门状态(0=正常&1=停用),char,N,1,N,0,,|" +
                    "del_flag,删除标记（0=代表存在&2=代表删除）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,32,N,,,|" +
                    "create_time,创建时间,datetime,N,,N,当前时间,,|" +
                    "update_by,更新者,varchar,N,32,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|"),

    SYS_ROLE("SYS_ROLE","3.角色信息表",
            "role_id,角色ID,varchar,Y,32,N,,,|" +
                    "role_name,角色名称,varchar,N,30,N,,,|" +
                    "role_key,角色权限字符串,varchar,N,100,N,,,|" +
                    "role_sort,显示顺序,int,N,4,N,,,|" +
                    "data_scope,数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）,char,N,1,N,1,,|" +
                    "status,帐号状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "del_flag,删除标志（0=代表存在&2=代表删除）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_POST("SYS_POST","4.岗位信息表",
            "post_id,岗位ID,varchar,Y,32,N,,,|" +
                    "post_code,岗位编码,varchar,N,64,N,,,|" +
                    "post_name,岗位名称,varchar,N,50,N,,,|" +
                    "post_sort,显示顺序,int,N,4,N,,,|" +
                    "status,帐号状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_USER_ROLE("SYS_USER_ROLE","5.用户角色关联表",
            "user_id,用户ID,varchar,Y,32,N,,,|" +
                    "role_id,角色ID,varchar,Y,32,N,,,|"),

    SYS_USER_POST("SYS_USER_POST","6.用户岗位关联表",
            "user_id,用户ID,varchar,Y,32,N,,,|" +
                    "post_id,岗位ID,varchar,Y,32,N,,,|"),

    SYS_MENU("SYS_MENU","7.系统菜单表",
            "menu_id,菜单ID,varchar,Y,32,N,,,|" +
                    "menu_name,菜单名称,varchar,N,50,N,,,|" +
                    "parent_id,父菜单ID,varchar,N,32,N,0,,|" +
                    "order_num,显示顺序,int,N,4,N,0,,|" +
                    "url,请求地址,varchar,N,300,N,空,,|" +
                    "target,打开方式（menuItem页签 menuBlank新窗口）,varchar,N,20,Y,menuItem,,|" +
                    "menu_type,菜单类型（M目录 C菜单 F按钮）,char,N,1,N,,,|" +
                    "visible,菜单状态（0显示 1隐藏）,char,N,1,N,0,,|" +
                    "perms,权限标识,varchar,N,100,Y,空,,|" +
                    "icon,菜单图标,varchar,N,50,Y,#,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_ROLE_MENU("SYS_ROLE_MENU","8.角色和菜单关联表",
            "role_id,角色ID,varchar,Y,32,N,,,|" +
                    "menu_id,菜单ID,varchar,Y,32,N,,,|"),

    SYS_ROLE_DEPT("SYS_ROLE_DEPT","9.角色和部门关联表",
            "role_id,角色ID,varchar,Y,32,N,,,|" +
                    "dept_id,部门ID,varchar,Y,32,N,,,|"),

    SYS_OPER_LOG("SYS_OPER_LOG","10.操作日志记录表",
            "oper_id,操作日志ID,varchar,Y,32,N,,,|" +
                    "title,模块标题,varchar,N,100,N,,,|" +
                    "business_type,业务类型（0其它 1新增 2修改 3删除）,int,N,2,N,0,,|" +
                    "method,方法名称,varchar,N,100,Y,空,,|" +
                    "request_method,请求方式,varchar,N,20,Y,空,,|" +
                    "operator_type,操作类别（0其它 1后台用户 2手机端用户）,char,N,1,N,0,,|" +
                    "oper_name,操作人员,varchar,N,50,Y,空,,|" +
                    "dept_name,部门名称,varchar,N,50,Y,空,,|" +
                    "oper_url,请求URL,varchar,N,500,Y,空,,|" +
                    "oper_ip,主机地址,varchar,N,128,Y,空,,|" +
                    "oper_location,操作地点,varchar,N,100,Y,空,,|" +
                    "oper_param,请求参数,varchar,N,2000,Y,空,,|" +
                    "json_result,返回参数,varchar,N,2000,Y,空,,|" +
                    "status,操作状态（0正常 1异常）,char,N,1,N,0,,|" +
                    "error_msg,错误消息,varchar,N,2000,Y,空,,|" +
                    "oper_time,操作时间,datetime,N,,Y,当前时间,,|"),


    SYS_DICT_TYPE("SYS_DICT_TYPE","11.字典类型表",
            "dict_id,字典类型主键ID,bigint,Y,32,N,,,|" +
                    "dict_name,字典类型名称,varchar,N,50,N,,,|" +
                    "dict_type,字典类型key,varchar,N,50,N,,,|" +
                    "status,帐号状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "type,类型(0系统字典，1业务字典，2自定义字典),char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_DICT_DATA("SYS_DICT_DATA","12.字典数据表",
            "dict_code,字典数据主键ID,varchar,Y,32,N,,,|" +
                    "dict_sort,字典排序,int,N,4,N,0,,|" +
                    "dict_label,字典标签,varchar,N,50,Y,空,,|" +
                    "dict_value,字典键值,varchar,N,50,Y,空,,|" +
                    "dict_type,字典类型,varchar,N,50,Y,空,,|" +
                    "css_class,样式属性（其他样式扩展）,varchar,N,100,Y,空,,|" +
                    "list_class,表格回显样式,varchar,N,100,Y,空,,|" +
                    "is_default,是否默认（Y是 N否）,char,N,1,N,N,,|" +
                    "status,状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_CONFIG("SYS_CONFIG","13.参数配置表",
            "config_id,参数表主键ID,varchar,Y,32,N,,,|" +
                    "config_name,参数名称,varchar,N,50,N,,,|" +
                    "config_key,参数键名,varchar,N,50,N,,,|" +
                    "config_value,参数键值,varchar,N,500,N,,,|" +
                    "config_type,系统内置（Y是 N否）,char,N,1,N,N,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_LOGININFOR("SYS_LOGININFOR","14.系统访问记录表",
            "info_id,访问记录主键ID,varchar,Y,32,N,,,|" +
                    "login_name,登录账号,varchar,N,32,Y,空,,|" +
                    "ipaddr,登录IP地址,varchar,N,128,Y,空,,|" +
                    "login_location,登录地点,varchar,N,100,Y,空,,|" +
                    "browser,浏览器,varchar,N,50,Y,空,,|" +
                    "os,操作系统,varchar,N,50,Y,空,,|" +
                    "status,登录状态（0成功 1失败）,char,N,1,N,0,,|" +
                    "msg,提示消息,varchar,N,500,Y,空,,|" +
                    "login_time,访问时间,datetime,N,,Y,当前时间,,|"),

    SYS_USER_ONLINE("SYS_USER_ONLINE","15.在线用户记录表",
            "sessionId,用户会话ID,varchar,Y,50,N,,,|" +
                    "login_name,登录账号,varchar,N,32,Y,空,,|" +
                    "dept_name,部门名称,varchar,N,50,Y,空,,|" +
                    "ipaddr,登录IP地址,varchar,N,128,Y,空,,|" +
                    "login_location,登录地点,varchar,N,100,Y,空,,|" +
                    "browser,浏览器,varchar,N,50,Y,空,,|" +
                    "os,操作系统,varchar,N,50,Y,空,,|" +
                    "status,在线状态(on_line在线 off_line离线),varchar,N,10,Y,空,,|" +
                    "start_timestamp,session创建时间,datetime,N,,Y,,,|" +
                    "last_access_time,session最后访问时间,datetime,N,,Y,,,|" +
                    "expire_time,超时时间，单位为分钟,int,N,5,Y,0,,|"),

    SYS_JOB("SYS_JOB","16.定时任务调度表",
            "job_id,任务主键ID,varchar,Y,32,N,,,|" +
                    "job_name,任务名称,varchar,N,100,N,空,,|" +
                    "job_group,任务组名,varchar,N,100,N,DEFAULT,,|" +
                    "invoke_target,调用目标字符串,varchar,N,2000,N,,,|" +
                    "cron_expression,cron执行表达式,varchar,N,200,N,,,|" +
                    "misfire_policy,计划执行错误策略（1立即执行 2执行一次 3放弃执行）,char,N,1,N,3,,|" +
                    "concurrent,是否并发执行（0允许 1禁止）,char,N,1,,1,,|" +
                    "status,状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    SYS_JOB_LOG("SYS_JOB_LOG","17.定时任务调度日志表",
            "job_log_id,任务日志ID,varchar,Y,32,N,,,|" +
                    "job_name,任务名称,varchar,N,100,N,,,|" +
                    "job_group,任务组名,varchar,N,100,N,,,|" +
                    "invoke_target,调用目标字符串,varchar,N,2000,N,,,|" +
                    "job_message,日志信息,varchar,N,2000,N,,,|" +
                    "status,执行状态（0正常 1失败）,char,N,1,N,0,,|" +
                    "exception_info,异常信息,varchar,N,2000,N,,,|" +
                    "create_time,创建时间,datetime,N,,N,当前时间,,|"),

    SYS_NOTICE("SYS_NOTICE","18.通知公告表",
            "notice_id,公告ID,varchar,Y,32,N,,,|" +
                    "notice_title,公告标题,varchar,N,100,N,,,|" +
                    "notice_type,公告类型（1通知 2公告）,char,N,1,N,,,|" +
                    "notice_content,公告内容,longtext,N,,Y,,,|" +
                    "status,状态（0=正常&1=停用）,char,N,1,N,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    GEN_TABLE("GEN_TABLE","19.代码生成业务表",
            "table_id,编号,bigint,Y,20,N,,,|" +
                    "table_name,表名称,varchar,N,50,Y,空,,|" +
                    "table_comment,表描述,varchar,N,500,Y,空,,|" +
                    "sub_table_name,关联子表的表名,varchar,N,64,Y,空,,|" +
                    "sub_table_fk_name,子表关联的外键名,varchar,N,64,Y,空,,|" +
                    "class_name,实体类名称,varchar,N,100,Y,空,,|" +
                    "tpl_category,使用的模板（crud单表操作 tree树表操作 sub主子表操作）,varchar,N,200,N,crud,,|" +
                    "package_name,生成包路径,varchar,N,100,Y,空,,|" +
                    "module_name,生成模块名,varchar,N,30,Y,空,,|" +
                    "business_name,生成业务名,varchar,N,30,Y,空,,|" +
                    "function_name,生成功能名,varchar,N,50,Y,空,,|" +
                    "function_author,生成功能作者,varchar,N,50,Y,空,,|" +
                    "gen_type,生成代码方式（0zip压缩包 1自定义路径）,char,N,1,N,0,,|" +
                    "data_source,代码数据源,varchar,N,10,Y, /,,|" +
                    "gen_path,生成路径（不填默认项目路径）,varchar,N,200,N, /,,|" +
                    "options,其它生成选项,varchar,N,1000,Y,空,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|" +
                    "remark,备注,varchar,N,500,Y,空,,|"),

    GEN_TABLE_COLUMN("GEN_TABLE_COLUMN","20.代码生成业务字段表",
            "column_id,编号,bigint,Y,20,N,,,|" +
                    "table_id,归属表编号,varchar,N,64,Y,空,,|" +
                    "column_name,列名称,varchar,N,200,Y,空,,|" +
                    "column_comment,列描述,varchar,N,500,Y,空,,|" +
                    "column_type,列类型,varchar,N,100,Y,空,,|" +
                    "java_type,JAVA类型,varchar,N,500,Y,空,,|" +
                    "java_field,JAVA字段名,varchar,N,200,Y,空,,|" +
                    "is_pk,是否主键（1是）,char,N,1,Y,空,,|" +
                    "is_increment,是否自增（1是）,char,N,1,Y,空,,|" +
                    "is_required,是否必填（1是）,char,N,1,Y,空,,|" +
                    "is_insert,是否为插入字段（1是）,char,N,1,Y,空,,|" +
                    "is_edit,是否编辑字段（1是）,char,N,1,Y,空,,|" +
                    "is_list,是否列表字段（1是）,char,N,1,Y,空,,|" +
                    "is_query,是否查询字段（1是）,char,N,1,Y,空,,|" +
                    "query_type,查询方式（等于、不等于、大于、小于、范围）,varchar,N,200,Y,EQ,,|" +
                    "html_type,显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）,varchar,N,200,Y,空,,|" +
                    "dict_type,字典类型,varchar,N,200,Y,空,,|" +
                    "sort,排序,int,N,11,Y,0,,|" +
                    "create_by,创建者,varchar,N,64,Y,空,,|" +
                    "create_time,创建时间,datetime,N,,Y,当前时间,,|" +
                    "update_by,更新者,varchar,N,64,Y,空,,|" +
                    "update_time,更新时间,datetime,N,,Y,当前时间,,|"),

    SYS_ERROR_LOG("SYS_ERROR_LOG","21.错误日志表",
            "id,主键ID,varchar,Y,32,N,,,|" +
                    "error_code,错误代码,varchar,N,100,,,,|" +
                    "error_type,异常类型,varchar,N,200,Y,,,|" +
                    "error_message,错误日志,longtext,N,,Y,,,|" +
                    "status,阅读状态（0=未读&1=已读）,char,N,1,Y,0,,|" +
                    "request_ip,请求IP,varchar,N,64,Y,,,|" +
                    "server_ip,异常服务器IP,varchar,N,64,Y,,,|" +
                    "service_id,异常服务器服务ID,varchar,N,100,Y,,,|" +
                    "operator,操作人,varchar,N,32,Y,,,|" +
                    "log_time,日志时间,datetime,N,,Y,,,|" +
                    "remark,备注,varchar,N,500,Y,,,|"),
    ;

    private String tableName;
    private String tableComment;
    private String dataCSV;

    TableEnum(String tableName, String tableComment, String dataCSV) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.dataCSV = dataCSV;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getDataCSV() {
        return dataCSV;
    }

    public void setDataCSV(String dataCSV) {
        this.dataCSV = dataCSV;
    }
}
