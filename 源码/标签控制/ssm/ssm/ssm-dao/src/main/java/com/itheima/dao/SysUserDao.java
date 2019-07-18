package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/26 12:09
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysUserDao {



    /***
     * 用户列表查询
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> list();

    /***
     * 增加用户
     * id:调用序列赋值
     * @param user
     * @return
     */
    @SelectKey(statement = "select user_seq.nextval from dual",resultType = Long.class,keyProperty = "id",before = true)
    @Insert("insert into sys_user(id,username,email,password,phoneNum,status)values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    int add(SysUser user);


    /****
     * 1）查询用户
     * @param id
     * @return
     */
    @Select("select * from sys_user where id=#{id}")
    @Results(
            //用它的目的是实现当前SysUser的roles属性查询
            value = {
                    @Result(property = "roles",
                            column ="id",
                            many =@Many(select = "com.itheima.dao.SysRoleDao.getByUserId",fetchType = FetchType.DEFAULT) )
            }
    )
    SysUser getById(Long id);


    /***
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username}")
    @Results(
            //用它的目的是实现当前SysUser的roles属性查询
            value = {
                    @Result(property = "roles",
                            column ="id",
                            many =@Many(select = "com.itheima.dao.SysRoleDao.getByUserId",fetchType = FetchType.DEFAULT) )
            }
    )
    SysUser getUserByUserName(String username);

    /***
     * 根据用户ID删除用户角色信息
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_role where userid=#{userId}")
    int deleteUserRoles(Long userId);

    /***
     * 增加用户角色信息
     * @param userId
     * @param id
     * @return
     */
    @Insert("insert into sys_user_role(userId,roleId)values(#{userId},#{roleId})")
    int addUserRole(@Param("userId")Long userId,@Param("roleId")Long id);
}
