package com.itheima.dao;

import com.itheima.domain.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:42
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysPermissionDao {


    /****
     * 权限列表
     * @return
     */
    @Select("select * from sys_permission")
    List<SysPermission> list();

    /****
     * 增加资源
     * @param sysPermission
     * @return
     */
    @SelectKey(statement = "select permission_seq.nextval from dual",resultType =Long.class,keyProperty = "id",before = true)
    @Insert("insert into sys_permission(id,permissionName,url,pid)values(#{id},#{permissionName},#{url},#{pid})")
    int add(SysPermission sysPermission);


    /****
     * 根据角色ID查询权限信息
     * @param rid
     * @return
     */
    @Select("select * from sys_permission sp, sys_role_permission srp where sp.id=srp.permissionid and srp.roleid=#{rid}")
    List<SysPermission> getByRoleId(Long rid);
}


