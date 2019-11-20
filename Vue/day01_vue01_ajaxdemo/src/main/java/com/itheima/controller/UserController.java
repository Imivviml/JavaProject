/**
 * FileName: UserController
 * Author:   #include
 * Date:     2019/11/19 10:50
 * Description:
 */
package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public User findById(Integer id){
        return userService.findById(id);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @RequestMapping("/updateUser.do")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
}
