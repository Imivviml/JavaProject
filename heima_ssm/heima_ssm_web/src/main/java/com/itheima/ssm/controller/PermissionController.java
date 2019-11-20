/**
 * FileName: PermissionController
 * Author:   #include
 * Date:     2019/10/21 22:41
 * Description:
 */
package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    /**
     * 查询用户资源权限信息的列表
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }


    /**
     * 添加用户资源权限的操作
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String savePermission(Permission permission) throws Exception{
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }

}
