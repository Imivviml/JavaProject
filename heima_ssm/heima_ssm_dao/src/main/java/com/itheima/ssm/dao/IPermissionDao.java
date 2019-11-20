package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("iPermissionDao")
public interface IPermissionDao {


    /**
     * 根据roleId数据在多对多关系表中查询出permission的信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(@Param("roleId)") String roleId) throws Exception;


    /**
     * 查询所有用户的权限信息
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;


    /**
     * 添加用户资源权限的持久层接口
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);

    /**
     * 查询每个角色还可以添加的权限信息
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(@Param("roleId") String roleId) throws Exception;
}
