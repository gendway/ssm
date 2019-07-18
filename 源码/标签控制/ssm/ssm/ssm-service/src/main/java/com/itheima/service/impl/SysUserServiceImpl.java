package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.SysUserDao;
import com.itheima.domain.SysUser;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/26 12:08
 * description:深圳黑马
 * version:1.0
 ******/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser getUserByUserName(String username) {
        return sysUserDao.getUserByUserName(username);
    }

    /****
     * 用户列表查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<SysUser> list(int page, int size) {
        //a.调用PageHelper.startPage() 实现分页
        PageHelper.startPage(page,size);

        //b.查询所有
        List<SysUser> users = sysUserDao.list();

        //c.封装一个PageInfo<T>
        return new PageInfo<SysUser>(users);
    }

    @Override
    public int add(SysUser user) {
        return sysUserDao.add(user);
    }


    @Override
    public SysUser getById(Long id) {
        return sysUserDao.getById(id);
    }

    /****
     * 修改用户角色信息
     * @param userId
     * @param ids
     * @return
     */
    @Override
    public int updateUserRoles(Long userId, List<Long> ids) {
        //1)先删除之前所有数据
        int count = sysUserDao.deleteUserRoles(userId);

        //2)添加新数据
        for (Long id : ids) {
            count+=sysUserDao.addUserRole(userId,id);
        }
        return count;
    }
}
