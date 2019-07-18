package com.itheima.service;

import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/26 11:52
 * description:深圳黑马
 * version:1.0
 ******/
@Component(value = "userServicesDetailImpl")
public class UserServicesDetailImpl implements UserDetailsService {


    @Autowired
    private SysUserService sysUserService;


    /***
     * 根据用户名字查询用户信息
     * @param  username:用户提交的账号
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //去数据库查询
        /*SysUser sysUser = new SysUser();
        sysUser.setUsername("xiaohong");
        sysUser.setPassword("123456");*/

        //从数据库中查询
        SysUser sysUser = sysUserService.getUserByUserName(username);


        //如果用户不为空
        if(sysUser!=null){
            //创建一个集合，给用户授权
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            //查询用户角色  使用@Many
            List<SysRole> roles = sysUser.getRoles();
            if(roles!=null){
                for (SysRole role : roles) {
                    //从数据库中获取的角色
                    //grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
            }

            //创建User
            //User user = new User(sysUser.getUsername(),"{noop}"+sysUser.getPassword(),grantedAuthorities);
            User user = new User(sysUser.getUsername(),sysUser.getPassword(),grantedAuthorities);
            return user;
        }

       return  null;
    }


}
