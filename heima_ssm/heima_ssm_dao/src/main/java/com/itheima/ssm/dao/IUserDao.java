package com.itheima.ssm.dao;

import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("iUserDao")
public interface IUserDao {


    /**
     * 查询登录用户的信息和权限
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(@Param("username") String username) throws Exception;


    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    List<UserInfo> findAll() throws Exception;


    /**
     * 添加用户的持久层操作
     * @param userInfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);


    /**
     * 查询用户的详细信息的操作
     * @param id
     * @return
     */
    @Select("select * from users users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))

    })
    UserInfo findById(@Param("id") String id);


    /**
     * 给用户添加角色的持久层接口
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")String userId, @Param("roleId")String roleId) throws Exception;

    /**
     * 根据角色的id查询用户的详细信息
     * @param roleId
     * @return
     */
    @Select("select * from users where id in (select userId from users_role where roleId = #{roleId})")
    public List<UserInfo> findByRoleId(@Param("roleId") String roleId);
}
