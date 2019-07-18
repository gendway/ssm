package com.itheima.service;

import com.itheima.domain.SysPermission;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:41
 * description:深圳黑马
 * version:1.0
 ******/
public interface SysPermissionService {
    List<SysPermission> list();

    int add(SysPermission sysPermission);
}
