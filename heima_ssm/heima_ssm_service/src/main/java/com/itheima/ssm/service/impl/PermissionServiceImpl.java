/**
 * FileName: PermissionServiceImpl
 * Author:   #include
 * Date:     2019/10/21 22:47
 * Description:
 */
package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("iPermissionService")
public class PermissionServiceImpl implements IPermissionService {


    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有权限的信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    /**
     * 添加用户资源权限的业务层方法
     * @param permission
     * @throws Exception
     */
    @Override
    public void savePermission(Permission permission) throws Exception {
        permissionDao.savePermission(permission);
    }

    /**
     * 根据角色的Id查询还可以添加的权限信息
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception{
        return permissionDao.findOtherPermission(roleId);
    }
}
