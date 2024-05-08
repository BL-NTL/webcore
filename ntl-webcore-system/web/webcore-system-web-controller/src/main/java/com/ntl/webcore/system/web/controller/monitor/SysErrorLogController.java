package com.ntl.webcore.system.web.controller.monitor;

import com.ntl.webcore.common.lang.constant.SysDictConstants;
import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.common.web.annotation.Log;
import com.ntl.webcore.common.web.enums.BusinessType;
import com.ntl.webcore.common.web.model.AjaxResult;
import com.ntl.webcore.framework.page.domain.TableDataInfo;
import com.ntl.webcore.system.common.model.entity.SysErrorLog;
import com.ntl.webcore.system.common.model.util.poi.ExcelUtil;
import com.ntl.webcore.system.service.monitor.ISysErrorLogService;
import com.ntl.webcore.system.web.manager.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 错误日志Controller
 *
 * @author ntl
 * @date 2023-06-20
 */
@Controller
@RequestMapping("/monitor/sysErrorLog")
public class SysErrorLogController extends BaseController
{
    private String prefix = "monitor/errorLog";

    @Autowired
    private ISysErrorLogService sysErrorLogService;

    @RequiresPermissions("monitor:sysErrorLog:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/sysErrorLog";
    }

    /**
     * 查询错误日志列表
     */
    @RequiresPermissions("monitor:sysErrorLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysErrorLog sysErrorLog)
    {
        startPage();
        if(StrUtils.isEmpty(sysErrorLog.getStatus())){
            sysErrorLog.setStatus(SysDictConstants.SYS_READ_STATUS_NOT_READ);
        }

        List<SysErrorLog> list = sysErrorLogService.selectSysErrorLogList(sysErrorLog);
        return getDataTable(list);
    }

    /**
     * 导出错误日志列表
     */
    @RequiresPermissions("monitor:sysErrorLog:export")
    @Log(title = "错误日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysErrorLog sysErrorLog)
    {
        List<SysErrorLog> list = sysErrorLogService.selectSysErrorLogList(sysErrorLog);
        ExcelUtil<SysErrorLog> util = new ExcelUtil<SysErrorLog>(SysErrorLog.class);
        return util.exportExcel(list, "错误日志数据");
    }

    /**
     * 新增错误日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存错误日志
     */
    @RequiresPermissions("monitor:sysErrorLog:add")
    @Log(title = "错误日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysErrorLog sysErrorLog)
    {
        return toAjax(sysErrorLogService.insertSysErrorLog(sysErrorLog));
    }

    /**
     * 修改错误日志
     */
    @RequiresPermissions("monitor:sysErrorLog:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        SysErrorLog sysErrorLog = sysErrorLogService.selectSysErrorLogById(id);
        mmap.put("sysErrorLog", sysErrorLog);
        return prefix + "/edit";
    }

    @RequiresPermissions("monitor:sysErrorLog:view")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        SysErrorLog sysErrorLog = sysErrorLogService.selectSysErrorLogById(id);
        mmap.put("sysErrorLog", sysErrorLog);
        return prefix + "/sysErrorLogDetail";
    }

    /**
     * 修改保存错误日志
     */
    @RequiresPermissions("monitor:sysErrorLog:edit")
    @Log(title = "错误日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysErrorLog sysErrorLog)
    {
        return toAjax(sysErrorLogService.updateSysErrorLog(sysErrorLog));
    }

    /**
     * 删除错误日志
     */
    @RequiresPermissions("monitor:sysErrorLog:remove")
    @Log(title = "错误日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysErrorLogService.deleteSysErrorLogByIds(ids));
    }

    @RequiresPermissions("monitor:sysErrorLog:edit")
    @Log(title = "错误日志", businessType = BusinessType.UPDATE)
    @PostMapping( "/markread")
    @ResponseBody
    public AjaxResult markAllRead(String ids)
    {
        return toAjax(sysErrorLogService.markAllReadByIds(ids));
    }

}