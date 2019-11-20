/**
 * FileName: UserTest
 * Author:   #include
 * Date:     2019/11/19 9:35
 * Description:
 */
package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = userService.findById(1);
        System.out.println(user);
    }


    @Test
    public void testUpdate(){
        User user = userService.findById(1);
        System.out.println("修改之前的用户:" + user);
        user.setAge(45);
        userService.updateUser(user);
        User user1 = userService.findById(1);
        System.out.println("修改之后的用户:" + user1);
    }
}
