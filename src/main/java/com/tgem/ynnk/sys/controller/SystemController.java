package com.tgem.ynnk.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgem.ynnk.controller.BaseController;
import com.tgem.ynnk.response.ProfileResult;
import com.tgem.ynnk.response.Result;
import com.tgem.ynnk.response.ResultCode;
import com.tgem.ynnk.sys.entity.User;
import com.tgem.ynnk.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SystemController extends BaseController {
    @Resource
    private UserService userService;


    @RequestMapping("/login")
    public Result login(String account, String password,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stime){
        try {
            System.out.println(stime);
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
//            password = new Md5Hash(password, userName, 3).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(account, password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //5.构造返回结果
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    @RequestMapping("/register")
    public Result register(String userName, String password){
        User user =new User();
        user.setUsername(userName);
        user.setPassword(password);
        boolean flag =  userService.save(user);
        return new Result(ResultCode.SUCCESS,user);
    }

    @RequestMapping(value = "/test")
    @RequiresPermissions(value = "API-USER-DELETE")
    public Result testPers(){
        Subject subject = SecurityUtils.getSubject();

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/test2")
    @RequiresPermissions(value = "API-USER-UPDATE")
    public Result test2(){
        Subject subject = SecurityUtils.getSubject();

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @RequiresAuthentication
    public Result findUser(){
        Subject subject = SecurityUtils.getSubject();
        ProfileResult result = (ProfileResult)subject.getPrincipal();

        return new Result(ResultCode.SUCCESS,result);
    }

    @RequestMapping("/logOut")
    public Result logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultCode.SUCCESS);

    }

}
