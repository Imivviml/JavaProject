package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    /**
     * 查询所有角色的信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Role> findAll(int page,int size) throws Exception;

    /**
     * 添加角色操作
      * @param role
     */
    void saveRole(Role role) throws Exception;

    /**
     * 查询用户是否还有可以添加的角色信息
     * @param userId
     */
    List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * 根据角色的Id查询角色
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;


    /**
     * 保存角色添加的权限信息
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
