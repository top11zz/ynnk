package com.tgem.ynnk.sys.controller;


import com.tgem.ynnk.response.Result;
import com.tgem.ynnk.response.ResultCode;
import com.tgem.ynnk.sys.entity.Role;
import com.tgem.ynnk.sys.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tgem.ynnk.controller.BaseController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/findById")
    public Result findById(String id){
        Role role = roleService.findByRoleId(id);
        return new Result(ResultCode.SUCCESS,role);
    }

    @RequestMapping("/findPerIds")
    public Result findPermissionIds(String id){
        Set<String> perIds = roleService.findperIdsByRoleId(id);
        return new Result(ResultCode.SUCCESS,perIds);
    }

    @RequestMapping("/assignPerms")
    @RequiresPermissions("ROLE-UPDATE")
    public Result assignPerms(@RequestParam String id,@RequestParam List<String> perIds){
        roleService.assignPerms(id,perIds);
        return new Result(ResultCode.SUCCESS);
    }
}

