package com.ntl.webcore.system.web.controller.system;

import com.ntl.webcore.common.lang.constant.UserConstants;
import com.ntl.webcore.common.lang.constant.SysDictConstants;
import com.ntl.webcore.common.web.annotation.Log;
import com.ntl.webcore.common.web.enums.BusinessType;
import com.ntl.webcore.common.web.model.AjaxResult;
import com.ntl.webcore.framework.page.domain.TableDataInfo;
import com.ntl.webcore.system.common.model.entity.SysDictType;
import com.ntl.webcore.system.common.model.util.poi.ExcelUtil;
import com.ntl.webcore.system.common.model.view.Ztree;
import com.ntl.webcore.system.service.base.ISysDictTypeService;
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
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController
{
    private String prefix = "system/dict/type";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictType()
    {
        return prefix + "/type";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictType dictType)
    {

        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "字典类型");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(getLoginName());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        mmap.put("dictList", dictTypeService.selectDictTypeAll());
        return "system/dict/data/data";
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions(value={"system:dict:edit", "system:businessUserDict:edit"}, logical= Logical.OR)
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions(value={"system:dict:edit", "system:businessUserDict:edit", "system:businessCommonDict:edit"}, logical= Logical.OR)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(getLoginName());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions(value={"system:dict:remove", "system:businessUserDict:remove", "system:businessCommonDict:remove"}, logical= Logical.OR)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        dictTypeService.deleteDictTypeByIds(ids);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @RequiresPermissions(value={"system:dict:remove", "system:businessUserDict:remove","system:businessCommonDict:remove"}, logical= Logical.OR)
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    @ResponseBody
    public AjaxResult refreshCache()
    {
        dictTypeService.resetDictCache();
        return success();
    }



    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(SysDictType dictType)
    {
        return dictTypeService.checkDictTypeUnique(dictType);
    }

    /**
     * 选择字典树
     */
    @GetMapping("/selectDictTree/{columnId}/{dictType}")
    public String selectDeptTree(@PathVariable("columnId") Long columnId, @PathVariable("dictType") String dictType,
            ModelMap mmap)
    {
        mmap.put("columnId", columnId);
        mmap.put("dict", dictTypeService.selectDictTypeByType(dictType));
        return prefix + "/tree";
    }

    /**
     * 加载字典列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dictTypeService.selectDictTree(new SysDictType());
        return ztrees;
    }

    @RequiresPermissions("system:businessCommonDict:view")
    @GetMapping("/business/common")
    public String viewBusinessCommonDictType()
    {
        return prefix + "/business_common_dicttype";
    }

    @PostMapping("/business/common/list")
    @RequiresPermissions("system:businessCommonDict:list")
    @ResponseBody
    public TableDataInfo listBusinessCommon(SysDictType dictType)
    {
        startPage();
        dictType.setType(SysDictConstants.TYPE_BUSINESS_COMMON);
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @RequiresPermissions("system:businessCommonDict:list")
    @GetMapping("/detail/business/common/{dictId}")
    public String detailBusinessCommon(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        SysDictType dictType = new SysDictType();
        dictType.setType(SysDictConstants.TYPE_BUSINESS_COMMON);
        mmap.put("dictList", dictTypeService.selectDictTypeList(dictType));
        return "system/dict/data/business_common_data";
    }


    @GetMapping("/business/common/add")
    @RequiresPermissions("system:businessCommonDict:add")
    public String addBusinessCommon()
    {
        return prefix + "/add_business_common";
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:businessCommonDict:add")
    @PostMapping("/business/common/add")
    @ResponseBody
    public AjaxResult addBusinessCommonSave(@Validated SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(getLoginName());
        dict.setType(SysDictConstants.TYPE_BUSINESS_COMMON);
        return toAjax(dictTypeService.insertDictType(dict));
    }

    @RequiresPermissions("system:businessCommonDict:edit")
    @GetMapping("/business/common/edit/{dictId}")
    public String editBusinessCommon(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit_business_common";
    }

    @RequiresPermissions("system:businessUserDict:view")
    @GetMapping("/business/user")
    public String businessUserDictType()
    {
        return prefix + "/business_user_dicttype";
    }

    @RequiresPermissions("system:businessUserDict:list")
    @PostMapping("/business/user/list")
    @ResponseBody
    public TableDataInfo listBusinessUser(SysDictType dictType)
    {
        startPage();
        dictType.setType(SysDictConstants.TYPE_BUSINESS_USER);
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @RequiresPermissions("system:businessUserDict:add")
    @GetMapping("/business/user/add")
    public String addBusinessUser()
    {
        return prefix + "/add_business_user";
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:businessUserDict:add")
    @PostMapping("/business/user/add")
    @ResponseBody
    public AjaxResult addBusinessUserSave(@Validated SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(getLoginName());
        dict.setType(SysDictConstants.TYPE_BUSINESS_USER);
        return toAjax(dictTypeService.insertDictType(dict));
    }

    @RequiresPermissions("system:businessUserDict:edit")
    @GetMapping("/business/user/edit/{dictId}")
    public String editBusinessUser(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit_business_user";
    }

    @RequiresPermissions("system:businessUserDict:list")
    @GetMapping("/detail/business/user/{dictId}")
    public String detailBusinessUser(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        SysDictType dictType = new SysDictType();
        dictType.setType(SysDictConstants.TYPE_BUSINESS_USER);
        mmap.put("dictList", dictTypeService.selectDictTypeList(dictType));
        return "system/dict/data/business_user_data";
    }

}
