package com.itheima.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/********
 * author:shenkunlin
 * date:2018/8/27 8:59
 * description:深圳黑马
 * version:1.0
 ******/
public class SysPermission implements Serializable {

    private Long id;
    private String permissionName;
    private String url;
    private Long pid;

    //子父关系
    private List<SysPermission> child;

    //一对多关系
    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public List<SysPermission> getChild() {
        return child;
    }

    public void setChild(List<SysPermission> child) {
        this.child = child;
    }
}
