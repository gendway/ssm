package com.itheima.service;

import com.itheima.domain.SysLog;

/********
 * author:shenkunlin
 * date:2018/8/29 12:01
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysLogService {

    /***
     * 添加日志
     * @param log
     * @return
     */
    int addLog(SysLog log);

}
