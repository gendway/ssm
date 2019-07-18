package com.itheima.service;

import com.itheima.domain.SysRole;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:10
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysRoleService {
    /****
     * 角色查询
     * @return
     */
    List<SysRole> list();

    int add(SysRole role);
}
