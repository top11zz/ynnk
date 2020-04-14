package com.tgem.ynnk.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tgem.ynnk.response.ProfileResult;
import com.tgem.ynnk.sys.dao.PermissionMapper;
import com.tgem.ynnk.sys.dao.RoleMapper;
import com.tgem.ynnk.sys.dao.UserMapper;
import com.tgem.ynnk.sys.entity.Permission;
import com.tgem.ynnk.sys.entity.Role;
import com.tgem.ynnk.sys.entity.User;
import com.tgem.ynnk.sys.service.PermissionService;
import com.tgem.ynnk.sys.service.RoleService;
import com.tgem.ynnk.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

//公共的realm：获取安全数据，构造权限信息
public class MyRealm  extends AuthorizingRealm {
   @Resource
   private UserMapper userMapper;

   @Resource
   private RoleMapper roleMapper;

   @Resource
   private PermissionMapper permissionMapper;

    public void setName(String name) {
        super.setName("myRealm");
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws UnauthorizedException {
        //1.获取安全数据
        ProfileResult result = (ProfileResult)principalCollection.getPrimaryPrincipal();
        //2.获取权限信息
        Set<String> pointsPerms = (Set<String>)result.getRoles().get("points");
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        if(null!=pointsPerms&&pointsPerms.size()>0) {
            info.addStringPermissions(pointsPerms);
        }
        return info;
    }

    //认证方法
    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String account = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //2.根据手机号查询用户
//        User user = userService.findByMobile(mobile);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("password",account);
        User user = userMapper.selectOne(queryWrapper);

        //3.判断用户是否存在，用户密码是否和输入密码一致
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
//            ProfileResult result = null;
//            if("user".equals(user.getLevel())) {
//                result = new ProfileResult(user);
//            }else {
//                Map map = new HashMap();
//                if("coAdmin".equals(user.getLevel())) {
//                    map.put("enVisible","1");
//                }
//                List<Permission> list = permissionService.findAll(map);

        if(user!=null) {
            ProfileResult result = null;
            if(user.getAccount().equals("admin")){
                List<Permission> permissions = permissionMapper.selectList(null);
               result = new ProfileResult(user,permissions);
            }else {
                Set<Role> roles = roleMapper.findByUserId(user.getId());
                String[] roleIds = new String[roles.size()];
                for (Role role : roles
                ) {
                    Set<Permission> permissions = permissionMapper.findByRoleId(role.getId());
                    role.setPermissions(permissions);
                }
                user.setRoles(roles);

               result = new ProfileResult(user);
            }
//            }
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,user.getPassword(),this.getName());
            return info;
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }
}
