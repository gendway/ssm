package com.itheima.aop;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/********
 * author:shenkunlin
 * date:2018/8/29 12:06
 * description:深圳黑马
 * version:1.0
 ******/
@Aspect
@Component
public class LogHandler {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;


    /***
     * 前置通知
     * 做日志记录
     */
    @Before("execution(* com.itheima.controller.*Controller.*(..))")
    public void beforeLog(JoinPoint jp){
        //签名信息
        addLogHandler(jp);
    }


    /****
     * 后置通知
     */
    @After("execution(* com.itheima.controller.*Controller.*(..))")
    public void afterLog(JoinPoint jp){
        addLogHandler(jp);
    }


    /***
     * 添加日志
     * @param jp
     */
    public void addLogHandler(JoinPoint jp) {
        //JoinPoint记录了调用切点的所有信息

        //签名信息
        Signature signature = jp.getSignature();

        //获取被调用的方法名
        String methodName = signature.getName();

        //获取当前被调用的方法所在的类
        Class clazz = signature.getDeclaringType();
        System.out.println(clazz.getName());

        //获取用户调方法的参数
        Object[] args = jp.getArgs();


        //封装log对象
        SysLog sysLog = new SysLog();
        sysLog.setMethod(clazz.getName()+"."+methodName);
        sysLog.setUsername(getUserName());
        sysLog.setIp(request.getRemoteAddr());  //获取用户IP
        sysLog.setVisitTime(new Date());

        sysLogService.addLog(sysLog);
    }

    /***
     * 获取用户账号
     * @return
     */
    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
