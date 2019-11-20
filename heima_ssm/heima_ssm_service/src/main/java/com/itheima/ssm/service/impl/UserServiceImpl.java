/**
 * FileName: UserServiceImpl
 * Author:   #include
 * Date:     2019/10/18 17:46
 * Description:
 */
package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理自己的用户对象userInfo封装成UserDetails对象
        //User user = new User(userInfo.getUsername(),"{noop}" + userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,
                true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {

            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return list;
    }


    /**
     * 查询所有用户的方法
     * @return
     */
    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception{
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }


    /**
     * 添加用户的业务操作
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void saveUser(UserInfo userInfo) throws Exception {

        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }


    /**
     * 查询用户的详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 根据用户的id和可以给该用户添加角色的业务层方法
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {

        //遍历可以添加的角色Id
        for (String roleId : roleIds) {

            userDao.addRoleToUser(userId,roleId);
        }
    }
}
