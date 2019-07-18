package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/26 11:37
 * description:深圳黑马
 * version:1.0
 ******/
public class SysUser implements Serializable {


    private Long id;
    private String username;        //账号
    private String email;
    private String password;        //密码
    private String phoneNum;
    private int status;

    //一个用户拥有多个角色,针对用户而言,一对多[相对于用户和角色之间，关系属于多对多]
    private List<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
