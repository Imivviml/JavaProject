package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface IUserService {

    /**
     * 查询用列表
     * @return
     */
    public List<User> findAll();

    /**
     * 根据用户的id查询用户的信息
     * @param userId
     * @return
     */
    public User findById(Integer userId);


    /**
     * 修改用户的信息
     * @param user
     */
    public void updateUser(User user);

}
