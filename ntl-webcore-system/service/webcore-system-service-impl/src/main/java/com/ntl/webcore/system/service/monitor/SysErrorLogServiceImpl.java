package com.ntl.webcore.system.service.monitor;

import com.ntl.webcore.common.lang.text.Convert;
import com.ntl.webcore.common.lang.uuid.IdUtils;
import com.ntl.webcore.system.common.model.entity.SysErrorLog;
import com.ntl.webcore.system.mapper.SysErrorLogMapper;
import com.ntl.webcore.system.service.monitor.ISysErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 错误日志Service业务层处理
 *
 * @author ntl
 * @date 2023-06-20
 */
@Service
public class SysErrorLogServiceImpl implements ISysErrorLogService
{
    @Autowired
    private SysErrorLogMapper sysErrorLogMapper;

    /**
     * 查询错误日志
     *
     * @param id 错误日志主键
     * @return 错误日志
     */
    @Override
    public SysErrorLog selectSysErrorLogById(String id)
    {
        return sysErrorLogMapper.selectSysErrorLogById(id);
    }

    /**
     * 查询错误日志列表
     *
     * @param sysErrorLog 错误日志
     * @return 错误日志
     */
    @Override
    public List<SysErrorLog> selectSysErrorLogList(SysErrorLog sysErrorLog)
    {
        return sysErrorLogMapper.selectSysErrorLogList(sysErrorLog);
    }

    /**
     * 新增错误日志
     *
     * @param sysErrorLog 错误日志
     * @return 结果
     */
    @Override
    public int insertSysErrorLog(SysErrorLog sysErrorLog)
    {
        sysErrorLog.setId(IdUtils.fastSimpleUUID());
        return sysErrorLogMapper.insertSysErrorLog(sysErrorLog);
    }

    /**
     * 修改错误日志
     *
     * @param sysErrorLog 错误日志
     * @return 结果
     */
    @Override
    public int updateSysErrorLog(SysErrorLog sysErrorLog)
    {
        return sysErrorLogMapper.updateSysErrorLog(sysErrorLog);
    }

    /**
     * 批量删除错误日志
     *
     * @param ids 需要删除的错误日志主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorLogByIds(String ids)
    {
        return sysErrorLogMapper.deleteSysErrorLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除错误日志信息
     *
     * @param id 错误日志主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorLogById(String id)
    {
        return sysErrorLogMapper.deleteSysErrorLogById(id);
    }

    @Override
    public int markAllReadByIds(String ids)
    {
        return sysErrorLogMapper.markAllReadByIds(Convert.toStrArray(ids));
    }
}
