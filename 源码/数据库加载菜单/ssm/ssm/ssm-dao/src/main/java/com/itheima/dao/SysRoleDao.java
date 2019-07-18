package com.itheima.dao;

import com.itheima.domain.SysRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:11
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysRoleDao {

    /***
     * 查询角色列表
     * @return
     */
    @Select("select * from sys_role")
    List<SysRole> list();

    /***
     *
     * @param role
     * @return
     */
    @SelectKey(statement = "select role_seq.nextval from dual",resultType = Integer.class,before = true,keyProperty = "id")
    @Insert("insert into sys_role(id,rolename,roledesc)values(#{id},#{roleName},#{roleDesc})")
    int add(SysRole role);


    /***
     * 根据用户ID查询角色
     */
    @Select("select * from sys_role sr, sys_user_role sur where sr.id=sur.roleid and sur.userid=#{uid}")
    @Results(
            value = {
                    //给permissions属性赋值
                    @Result(property = "permissions",
                            column ="roleid" ,
                            many = @Many(select = "com.itheima.dao.SysPermissionDao.getByRoleId",fetchType = FetchType.DEFAULT))
            }
    )
    List<SysRole> getByUserId(Long uid);


}
