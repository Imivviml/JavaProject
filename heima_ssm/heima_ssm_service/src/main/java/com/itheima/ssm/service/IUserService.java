package com.itheima.ssm.service;


import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 查询所有用户的业务接口
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll(int page,int size) throws Exception;

    /**
     * 用户添加的业务层接口
     * @param userInfo
     * @throws Exception
     */
    void saveUser(UserInfo userInfo) throws Exception;

    /**
     * 查询用户信息的业务层接口
     * @param id
     * @return
     */
    UserInfo findById(String id) throws Exception;


    /**
     * 根据用户id和可以添加该用户的角色信息给该用户添加角色的业务层接口
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
