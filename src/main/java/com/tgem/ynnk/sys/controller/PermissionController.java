package com.tgem.ynnk.sys.controller;


import com.tgem.ynnk.response.ProfileResult;
import com.tgem.ynnk.response.Result;
import com.tgem.ynnk.response.ResultCode;
import com.tgem.ynnk.sys.entity.Permission;
import com.tgem.ynnk.sys.entity.PermissionMenu;
import com.tgem.ynnk.sys.entity.PermissionPoint;
import com.tgem.ynnk.sys.service.PermissionService;
import com.tgem.ynnk.sys.service.impl.PermissionServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tgem.ynnk.controller.BaseController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController extends BaseController {
    @Resource
    private PermissionService permissionService;

    @RequestMapping("/addPer")
    public Result addPer(Permission permission,  PermissionPoint permissionPoint, PermissionMenu permissionMenu) throws Exception {

        permissionService.add(permission,permissionMenu,permissionPoint);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping("/findTree")
    public Result findTree() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
        List<Permission> permissions = permissionService.findByTree(result.getRoles());
        return new Result(ResultCode.SUCCESS, permissions);
    }
}

