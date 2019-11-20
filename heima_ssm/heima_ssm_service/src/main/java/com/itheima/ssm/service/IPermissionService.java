package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询所有权限的信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<Permission> findAll(int page,int size) throws Exception;


    /**
     * 保存添加的权限信息
     * @param permission
     * @throws Exception
     */
    void savePermission(Permission permission) throws Exception;


    /**
     * 查询角色还可以添加的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId) throws Exception;
}
