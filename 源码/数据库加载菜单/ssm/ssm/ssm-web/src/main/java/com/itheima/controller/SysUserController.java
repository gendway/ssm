package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.SysRoleService;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/27 9:08
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SysRoleService sysRoleService;


    /***
     * 保存用户
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/role/modify",method = RequestMethod.POST)
    public String modify(Long userId,@RequestParam(value = "ids")List<Long> ids){
        //将用户信息存入用户角色中间表
        int mcount = sysUserService.updateUserRoles(userId,ids);
        return "redirect:/user/list";
    }

    /***
     * 用户角色变更
     * @param userid
     * @return
     */
    @RequestMapping(value = "/role/modify",method = RequestMethod.GET)
    public String modify(Long userid,Model model){
        //查询服务中所有角色信息
        List<SysRole> roles = sysRoleService.list();

        //把用户信息查询出来,懒加载角色信息
        SysUser sysUser = sysUserService.getById(userid);

        //用户所有角色
        List<SysRole> userRoles = sysUser.getRoles();

        StringBuffer buffer = new StringBuffer();
        for (SysRole userRole : userRoles) {
            //ROLE_USER,ROLE_ADMIN
            //将角色名字循环拼接
            buffer.append(userRole.getRoleName()+",");
        }
        //将字符存入Model
        model.addAttribute("rolestr",buffer.toString());

        //将数据存入Model---角色信息+保存用户ID
        model.addAttribute("roles",roles);
        model.addAttribute("userid",userid);
        return "user-role-add";
    }


    /***
     * 查询用户信息
     */
    @RequestMapping(value = "/one")
    public String getById(Long id,Model model){
        //查询用户信息
        SysUser sysUser = sysUserService.getById(id);

        //将用户信息保存
        model.addAttribute("sysUser",sysUser);
        return "user-show";
    }



    /***
     * 增加用户
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(SysUser user){
        //加密用户密码
        String pwd = encoder.encode(user.getPassword());
        user.setPassword(pwd);

        //调用Service增加数据
        int acount = sysUserService.add(user);

        //返回列表页
        return "redirect:/user/list";
    }

    /******
     * 用户列表查询
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page",required = false,defaultValue = "1")int page,
                       @RequestParam(value = "size",required = false,defaultValue = "5")int size,
                       Model model
                       ){
        //查询用户信息，返回PageInfo
        PageInfo<SysUser> pageInfo = sysUserService.list(page,size);

        //数据存入Model
        model.addAttribute("pageInfo",pageInfo);


        //获取用户登录信息
        SecurityContext context = SecurityContextHolder.getContext();

        //获取授权信息
        Authentication authentication = context.getAuthentication();

        //获取登录信息
        User user = (User) authentication.getPrincipal();
        System.out.println("-----方式1："+user.getUsername());

        System.out.println("-----方式2："+SecurityContextHolder.getContext().getAuthentication().getName());

        return "user-list";
    }

}
