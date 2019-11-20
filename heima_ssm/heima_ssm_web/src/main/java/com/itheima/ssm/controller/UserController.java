/**
 * FileName: UserController
 * Author:   #include
 * Date:     2019/10/20 13:39
 * Description:
 */
package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }


    //用户添加的controller
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'tom' ")
    public String save(UserInfo userInfo) throws Exception{
        userService.saveUser(userInfo);
        //使用关键字进行重定向
        return "redirect:findAll.do";
    }


    /**
     *  根据用户的id查询用户的详细信息
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show-new");
        return mv;
    }


    /**
     * 查询用户,以及用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据页面传过来的id查询用户的信息
        UserInfo userInfo = userService.findById(userId);
        //2.根据userId查询可以添加的角色
        List<Role> otherRoles = roleService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }


    /**
     * 根据页面响应过来的用户id和可以添加的角色信息的id来给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

}
