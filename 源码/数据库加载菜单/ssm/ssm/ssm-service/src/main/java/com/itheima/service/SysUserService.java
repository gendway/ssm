package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/26 12:08
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysUserService {
    /***
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);

    /***
     * 查询用户列表
     * @param page
     * @param size
     * @return
     */
    PageInfo<SysUser> list(int page, int size);

    /***
     * 增加用户
     * @param user
     * @return
     */
    int add(SysUser user);

    /****
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    SysUser getById(Long id);

    /***
     * 修改用户角色信息
     * @param userId
     * @param ids
     * @return
     */
    int updateUserRoles(Long userId, List<Long> ids);
}
