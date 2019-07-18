package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

/********
 * author:shenkunlin
 * date:2018/8/29 12:02
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysLogDao {

    /****
     * 增加日志
     * @param log
     * @return
     */
    @SelectKey(statement = "select log_seq.nextval from dual",resultType = Long.class,keyProperty = "id",before = true)
    @Insert("insert into sys_log(id,visitTime,username,ip,method)values(#{id},#{visitTime},#{username},#{ip},#{method})")
    int addLog(SysLog log);
}
