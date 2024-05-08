package com.ntl.webcore.system.service.monitor;

import com.ntl.webcore.system.common.model.entity.SysErrorLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 错误日志 Service interface
 *
 * @author ntl
 * @date 2023-06-20
 */
public interface ISysErrorLogService
{
    /**
     * 查询错误日志
     *
     * @param id 错误日志主键
     * @return 错误日志
     */
    public SysErrorLog selectSysErrorLogById(String id);

    /**
     * 查询错误日志列表
     *
     * @param sysErrorLog 错误日志
     * @return 错误日志集合
     */
    public List<SysErrorLog> selectSysErrorLogList(SysErrorLog sysErrorLog);

    /**
     * 新增错误日志
     *
     * @param sysErrorLog 错误日志
     * @return 结果
     */
    public int insertSysErrorLog(SysErrorLog sysErrorLog);

    /**
     * 修改错误日志
     *
     * @param sysErrorLog 错误日志
     * @return 结果
     */
    public int updateSysErrorLog(SysErrorLog sysErrorLog);

    /**
     * 批量删除错误日志
     *
     * @param ids 需要删除的错误日志主键集合
     * @return 结果
     */
    public int deleteSysErrorLogByIds(String ids);

    /**
     * 删除错误日志信息
     *
     * @param id 错误日志主键
     * @return 结果
     */
    public int deleteSysErrorLogById(String id);


    public int markAllReadByIds(String ids);
}