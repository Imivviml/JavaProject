/**
 * FileName: RoleController
 * Author:   #include
 * Date:     2019/10/21 15:54
 * Description:
 */
package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IPermissionService;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;
    /**
     * 查询所有角色的信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }


    /**
     * 添加角色的操作
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String saveRole(Role role) throws Exception{
        roleService.saveRole(role);
        return "redirect:findAll.do";
    }


    /**
     * 根据响应的角色Id查询角色,查询该角色可以添加的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        //1.根据页面响应过来的Id查询角色的信息
        Role role = roleService.findById(roleId);
        //2.查询角色可以添加的权限信息
        List<Permission> otherPermission = permissionService.findOtherPermission(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;

    }

    /**
     * 根据用户界面响应过来的id和响应的permission的id数组来保存添加的角色权限信息
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }


    /**
     * 根据角色id查询角色的详细信息
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show-new");
        return mv;
    }



}
