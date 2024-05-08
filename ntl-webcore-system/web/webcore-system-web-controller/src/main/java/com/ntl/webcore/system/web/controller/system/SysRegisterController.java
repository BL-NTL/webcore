package com.ntl.webcore.system.web.controller.system;

import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.common.web.model.AjaxResult;
import com.ntl.webcore.system.common.model.entity.SysUser;
import com.ntl.webcore.system.framework.service.SysRegisterService;
import com.ntl.webcore.system.service.base.ISysConfigService;
import com.ntl.webcore.system.web.manager.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 * 
 * 
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StrUtils.isEmpty(msg) ? success() : error(msg);
    }
}
