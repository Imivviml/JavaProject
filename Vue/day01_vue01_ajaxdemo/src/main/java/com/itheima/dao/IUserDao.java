package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * 用户的持久层接口
 */
@Repository("iUserDao")
public interface IUserDao {

    /**
     * 查询用列表
     * @return
     */
    @Select("select * from user")
    public List<User> findAll();

    /**
     * 根据用户的id查询用户的信息
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId} ")
    public User findById(@Param("userId")Integer userId);


    /**
     * 修改用户的信息
     * @param user
     */
    @Update("update user set username=#{username},password=#{password},age=#{age},sex=#{sex},email=#{email} where id=#{id}")
    public void updateUser(User user);
}
