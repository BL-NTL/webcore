package com.ntl.webcore.framework.page.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntl.webcore.common.web.utils.sql.SqlUtil;
import com.ntl.webcore.framework.page.domain.PageDomain;
import com.ntl.webcore.framework.page.domain.TableSupport;

/**
 * 分页工具类
 * 
 * 
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }
    public static void startPageUncheckSql(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        Page page = PageHelper.startPage(pageNum, pageSize);
        page.setUnsafeOrderBy(orderBy);
        page.setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
