package com.itheima.interceptor;

import com.itheima.domain.SysPermission;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/********
 * author:shenkunlin
 * date:2018/8/29 10:13
 * description:深圳黑马
 * version:1.0
 ******/
public class PermissionInterceptor implements HandlerInterceptor {


    @Autowired
    private SysUserService sysUserService;

    /***
     * 用户权限加载
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户信息
        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                String name = authentication.getName();

                //如果用户已经登录，则判断用户权限是否已经加载
                if (name != null && !"".equals(name)) {
                    //判断Session是否已经加载用户权限
                    Object sessionAllPermissions = request.getSession().getAttribute("parentPermission");

                    //如果已经加载就不管,否则执行加载
                    if (sessionAllPermissions == null) {
                        SysUser sysUser = sysUserService.getUserByUserName(name);

                        //将用户[权限]信息存入Session
                        List<SysRole> roles = sysUser.getRoles();

                        //存储所有权限
                        List<SysPermission> allPermissions = new ArrayList<SysPermission>();

                        //遍历获取权限
                        for (SysRole role : roles) {
                            //当前角色对应权限
                            List<SysPermission> permissions = role.getPermissions();

                            //将权限存入集合
                            allPermissions.addAll(permissions);
                        }

                        //权限重复
                        Set<SysPermission> sets = new HashSet<SysPermission>();
                        sets.addAll(allPermissions);
                        allPermissions.clear();
                        allPermissions.addAll(sets);

                        //筛选Pid=0的菜单
                        List<SysPermission> parentPermission = getParentPermission(allPermissions);

                        //循环筛选每个父节点的所有子节点
                        for (SysPermission sysPermission : parentPermission) {
                            findChild(sysPermission, allPermissions);
                        }

                        //将权限信息放入session
                        request.getSession().setAttribute("parentPermission", parentPermission);
                    }
                }
            }
        }
        return true;
    }


    /****
     * 查找权限的子集
     * @param permission
     * @param permissions
     */
    public void findChild(SysPermission permission, List<SysPermission> permissions) {
        //存储子集
        List<SysPermission> childPermission = new ArrayList<SysPermission>();

        //循环遍历集合，如果集合中的pid=permission的id，说明集合中被遍历的对象属于当前permission的子集
        for (SysPermission sysPermission : permissions) {
            //循环遍历集合，如果集合中的pid=permission的id，说明集合中被遍历的对象属于当前permission的子集
            if (sysPermission.getPid().longValue() == permission.getId().longValue()) {
                childPermission.add(sysPermission);

                //判断子集是否还有子集
                findChild(sysPermission, permissions);
            }
        }

        //设置子集
        permission.setChild(childPermission);
    }


    /***
     * 查找顶级结点
     * @return
     */
    public List<SysPermission> getParentPermission(List<SysPermission> permissions) {
        //存储顶级菜单结点
        List<SysPermission> parents = new ArrayList<SysPermission>();

        //循环遍历找出顶级结点
        for (SysPermission permission : permissions) {
            if (permission.getPid().longValue() == 0) {
                parents.add(permission);
            }
        }
        return parents;
    }
}
