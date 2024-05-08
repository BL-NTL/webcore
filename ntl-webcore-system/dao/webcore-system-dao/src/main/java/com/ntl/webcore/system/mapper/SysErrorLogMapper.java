package com.ntl.webcore.system.mapper;

import com.ntl.webcore.system.common.model.entity.SysErrorLog;

import java.util.List;

/**
 * 错误日志 Mapper Interface
 *
 * @author ntl
 * @date 2023-06-20
 */
public interface SysErrorLogMapper
{
    /**
     * Select 错误日志
     *
     * @param id 错误日志 primary key
     * @return 错误日志
     */
    public SysErrorLog selectSysErrorLogById(String id);

    /**
     * select 错误日志 list
     *
     * @param sysErrorLog 错误日志
     * @return 错误日志 list
     */
    public List<SysErrorLog> selectSysErrorLogList(SysErrorLog sysErrorLog);

    /**
     * add 错误日志
     *
     * @param sysErrorLog 错误日志
     * @return SysErrorLog
     */
    public int insertSysErrorLog(SysErrorLog sysErrorLog);

    /**
     * Modify 错误日志
     *
     * @param sysErrorLog 错误日志
     * @return record total
     */
    public int updateSysErrorLog(SysErrorLog sysErrorLog);

    /**
     * remove 错误日志
     *
     * @param id 错误日志 primary key
     * @return int
     */
    public int deleteSysErrorLogById(String id);

    /**
     * batch remove 错误日志
     *
     * @param ids primary key list
     * @return record total
     */
    public int deleteSysErrorLogByIds(String[] ids);

    public int markAllReadByIds(String[] ids);

}