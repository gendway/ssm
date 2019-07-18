package com.itheima.controller;

import com.itheima.domain.SysPermission;
import com.itheima.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:39
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/premission")
public class SysPremissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /***
     * 增加权限资源
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(SysPermission sysPermission){
        //调用Service
        int acount = sysPermissionService.add(sysPermission);

        //返回列表页
        return "redirect:/premission/list";
    }

    /****
     * 权限列表
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model){
        List<SysPermission> permissions = sysPermissionService.list();

        //存到Model
        model.addAttribute("permissions",permissions);
        return "permission-list";
    }

}
