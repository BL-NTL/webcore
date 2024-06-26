package com.ntl.webcore.quartz.util;

import java.util.Date;

import com.ntl.webcore.common.lang.constant.Constants;
import com.ntl.webcore.common.lang.constant.ScheduleConstants;
import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.common.web.utils.ExceptionUtil;
import com.ntl.webcore.common.web.utils.MessageUtils;
import com.ntl.webcore.common.web.utils.bean.BeanUtils;
import com.ntl.webcore.common.web.utils.spring.SpringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ntl.webcore.quartz.entity.SysJob;
import com.ntl.webcore.quartz.entity.SysJobLog;
import com.ntl.webcore.quartz.service.ISysJobLogService;

/**
 * 抽象quartz调用
 *
 * 
 */
public abstract class AbstractQuartzJob implements Job
{
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        SysJob sysJob = new SysJob();
        BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try
        {
            before(context, sysJob);
            if (sysJob != null)
            {
                doExecute(context, sysJob);
            }
            after(context, sysJob, null);
        }
        catch (Exception e)
        {
            log.error("任务执行异常  - ：", e);
            after(context, sysJob, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     */
    protected void before(JobExecutionContext context, SysJob sysJob)
    {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     */
    protected void after(JobExecutionContext context, SysJob sysJob, Exception e)
    {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setStartTime(startTime);
        sysJobLog.setEndTime(new Date());
        long runMs = sysJobLog.getEndTime().getTime() - sysJobLog.getStartTime().getTime();
        String jobMessage = MessageUtils.message("quartz.job.log.message.totalTime") + ":";

        if(runMs < 1000L){
            jobMessage += runMs + " " + MessageUtils.message("quartz.job.log.message.milliseconds") ;
        }else if(runMs < 60000L){
            jobMessage += (runMs / 1000) + " " + MessageUtils.message("quartz.job.log.message.seconds") ;
        }else{
            long minutes = (runMs / 1000) / 60;
            long seconds = (runMs / 1000) % 60;
            jobMessage +=  minutes + MessageUtils.message("quartz.job.log.message.minutes") ;
            jobMessage +=  seconds + MessageUtils.message("quartz.job.log.message.seconds") ;
        }
        sysJobLog.setJobMessage(jobMessage);
        if (e != null)
        {
            sysJobLog.setStatus(Constants.FAIL);
            //String errorMsg = StrUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            sysJobLog.setExceptionInfo(ExceptionUtil.getExceptionMessage(e));
        }
        else
        {
            sysJobLog.setStatus(Constants.SUCCESS);
        }

        // 写入数据库当中
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception;
}
