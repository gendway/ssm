package com.itheima.controller;

import com.itheima.domain.SysRole;
import com.itheima.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 10:08
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /****
     * 增加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(SysRole role){
        //sercie
        int acount =sysRoleService.add(role);
        //返回列表
        return "redirect:/role/list";
    }

    /***
     * 查询角色信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model){
        //service
        List<SysRole> roles = sysRoleService.list();

        //保存model
        model.addAttribute("roles",roles);
        return "role-list";
    }

}
