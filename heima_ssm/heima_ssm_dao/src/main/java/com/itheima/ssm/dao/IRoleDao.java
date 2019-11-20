/**
 * FileName: IRoleDao
 * Author:   #include
 * Date:     2019/10/19 14:15
 * Description:
 */
package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository("iRoleDao")
public interface IRoleDao {

    //根据用户的id查询所有的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(@Param("userId") String userId) throws Exception;

    //查询所欲角色的信息
    @Select("select * from role")
    public List<Role> findAll() throws Exception;


    //添加角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void saveRole(Role role) throws Exception;

    //查询用户还可以添加的角色
    @Select("select  * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(@Param("userId") String userId);

    //根据页面响应过来的roleId查询角色的详细信息
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findByRoleId")),
            @Result(property = "users",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IUserDao.findByRoleId"))
    })
    Role findById(@Param("roleId") String roleId);

    //给角色添加权限的持久层接口
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
