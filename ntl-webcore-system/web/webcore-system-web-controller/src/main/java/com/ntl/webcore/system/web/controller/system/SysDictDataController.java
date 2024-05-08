package com.ntl.webcore.system.web.controller.system;

import com.ntl.webcore.common.web.annotation.Log;
import com.ntl.webcore.common.web.enums.BusinessType;
import com.ntl.webcore.common.web.model.AjaxResult;
import com.ntl.webcore.framework.page.domain.TableDataInfo;
import com.ntl.webcore.system.common.model.entity.SysDictData;
import com.ntl.webcore.system.common.model.util.ShiroUtils;
import com.ntl.webcore.system.common.model.util.poi.ExcelUtil;
import com.ntl.webcore.system.service.base.ISysDictDataService;
import com.ntl.webcore.system.web.manager.controller.BaseController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 * 
 * 
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    private String prefix = "system/dict/data";

    @Autowired
    private ISysDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData()
    {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions(value={"system:dict:list", "system:businessCommonDict:list"}, logical= Logical.OR)
    @ResponseBody
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @PostMapping("/business/user/list")
    @RequiresPermissions("system:businessUserDict:list")
    @ResponseBody
    public TableDataInfo listBusinessUser(SysDictData dictData)
    {
        startPage();
        dictData.setCreateBy(ShiroUtils.getLoginName());
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap)
    {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 新增保存字典数据
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions(value={"system:dict:add", "system:businessCommonDict:add", "system:businessUserDict:add"}, logical= Logical.OR)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictData dict)
    {
        dict.setCreateBy(getLoginName());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典数据
     */
    @RequiresPermissions(value={"system:dict:edit", "system:businessUserDict:edit", "system:businessCommonDict:edit"}, logical=Logical.OR)
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap)
    {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典数据
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions(value={"system:dict:edit", "system:businessUserDict:edit", "system:businessCommonDict:edit"}, logical=Logical.OR)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictData dict)
    {
        dict.setUpdateBy(getLoginName());
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions(value={"system:dict:remove", "system:businessUserDict:remove", "system:businessCommonDict:remove"}, logical=Logical.OR)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        dictDataService.deleteDictDataByIds(ids);
        return success();
    }
}
