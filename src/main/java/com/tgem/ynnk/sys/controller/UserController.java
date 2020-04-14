package com.tgem.ynnk.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgem.ynnk.response.ProfileResult;
import com.tgem.ynnk.response.Result;
import com.tgem.ynnk.response.ResultCode;
import com.tgem.ynnk.sys.entity.User;
import com.tgem.ynnk.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tgem.ynnk.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("/findAll")
    public Result findAll(Integer pg, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stime, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate etime){

        Page page = new Page(pg,10);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("create_time",stime,etime);
        List<User>  users = userService.page(page, queryWrapper).getRecords();
        return new Result(ResultCode.SUCCESS,users);
    }

    @RequestMapping("/delUser")
    @RequiresPermissions("API-USER-DELETE")
    public Result delUser(String id){
        boolean flag = userService.removeById(id);
        return new Result(ResultCode.SUCCESS,flag);
    }

    @RequestMapping("/profile")
    public Result profile(){
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        //2.获取安全数据
        ProfileResult result = (ProfileResult)principals.getPrimaryPrincipal();

//        String userid = claims.getId();
//        //获取用户信息
//        User user = userService.findById(userid);
//        //根据不同的用户级别获取用户权限
//
//        ProfileResult result = null;
//
//        if("user".equals(user.getLevel())) {
//            result = new ProfileResult(user);
//        }else {
//            Map map = new HashMap();
//            if("coAdmin".equals(user.getLevel())) {
//                map.put("enVisible","1");
//            }
//            List<Permission> list = permissionService.findAll(map);
//            result = new ProfileResult(user,list);
//        }
        return new Result(ResultCode.SUCCESS,result);
    }

    @RequestMapping("/editUser")
    public Result editUser(String id){
        User user = new User();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("company_id",1);
        updateWrapper.set("company_id",2);
        boolean flag = userService.update(updateWrapper);
        return new Result(ResultCode.SUCCESS,flag);
    }

    @RequestMapping("/uploadPic")
    public Result uploadPic( MultipartFile file, String id) throws IOException {
        String picUrl = userService.uploadPic(file, id);
        return new Result(ResultCode.SUCCESS, picUrl);
    }
}

