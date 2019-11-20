/**
 * FileName: RoleServiceImpl
 * Author:   #include
 * Date:     2019/10/21 15:57
 * Description:
 */
package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    /**
     * 查询所有角色的信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }


    /**
     * 添加角色的业务层操作
     * @param role
     */
    @Override
    public void saveRole(Role role) throws Exception{
        roleDao.saveRole(role);
    }


    /**
     * 查询用户还可以添加的角色信息
     * @param userId
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return roleDao.findOtherRoles(userId);
    }

    /**
     * 根据页面响应过来的id查询角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public Role findById(String roleId) throws Exception{
        return roleDao.findById(roleId);
    }

    /**
     * 保存添加的角色权限信息
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
