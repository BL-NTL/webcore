package com.ntl.webcore.system.framework.realm;

import com.ntl.webcore.common.web.exception.user.*;
import com.ntl.webcore.system.common.model.entity.SysUser;
import com.ntl.webcore.system.common.model.util.ShiroUtils;
import com.ntl.webcore.system.framework.authorization.SmsCodeToken;
import com.ntl.webcore.system.framework.service.SysLoginService;
import com.ntl.webcore.system.service.base.ISysMenuService;
import com.ntl.webcore.system.service.base.ISysRoleService;
import com.ntl.webcore.system.service.base.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 * 
 * 
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        if(token instanceof UsernamePasswordToken
            || token instanceof SmsCodeToken){
            return true;
        }
        return super.supports(token);
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        SysUser user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("superadmin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        SysUser user = null;
        String userInfo = null;
        boolean isSms = false;
        SmsCodeToken smsToken = null;
        UsernamePasswordToken upToken = null;
        try
        {
            if(token instanceof SmsCodeToken){
                isSms = true;
                smsToken = (SmsCodeToken) token;
                userInfo = smsToken.getPhoneNumber();
                user = userService.selectUserByLoginName(smsToken.getPhoneNumber());
            }else{
                upToken = (UsernamePasswordToken) token;
                String username = upToken.getUsername();
                userInfo = username;
                String password = "";
                if (upToken.getPassword() != null)
                {
                    password = new String(upToken.getPassword());
                }
                user = loginService.login(username, password);
            }
        }
        catch (CaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.info("对用户[" + userInfo + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = null;
        if(isSms){
            info = new SimpleAuthenticationInfo(user, smsToken.getPhoneNumber(), getName());
        }else{
            info = new SimpleAuthenticationInfo(user, upToken.getPassword(), getName());
        }

        return info;
    }

    /**
     * 清理指定用户授权信息缓存
     */
    public void clearCachedAuthorizationInfo(Object principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理所有用户授权信息缓存
     */
    public void clearAllCachedAuthorizationInfo()
    {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null)
        {
            for (Object key : cache.keys())
            {
                cache.remove(key);
            }
        }
    }
}
