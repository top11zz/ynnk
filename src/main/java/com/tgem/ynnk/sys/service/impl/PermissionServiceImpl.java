package com.tgem.ynnk.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgem.ynnk.sys.dao.PermissionMapper;
import com.tgem.ynnk.sys.dao.PermissionMenuMapper;
import com.tgem.ynnk.sys.dao.PermissionPointMapper;
import com.tgem.ynnk.sys.entity.Permission;
import com.tgem.ynnk.sys.entity.PermissionMenu;
import com.tgem.ynnk.sys.entity.PermissionPoint;
import com.tgem.ynnk.sys.service.PermissionService;
import com.tgem.ynnk.util.BeanMapUtils;
import com.tgem.ynnk.util.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private PermissionMenuMapper permissionMenuMapper;
    @Resource
    private PermissionPointMapper permissionPointMapper;
    @Override
    public Set<Permission> findByRoleId(String roleId) {
        return permissionMapper.findByRoleId(roleId);
    }

    @Transactional
    public void add(Permission permission, PermissionMenu permissionMenu, PermissionPoint permissionPoint) throws Exception{
        permissionMapper.insert(permission);
        if(permission.getType()==1){
            permissionMenu.setId(permission.getId());
            permissionMenuMapper.insert(permissionMenu);
        }else{
            permissionPoint.setId(permission.getId());
            permissionPointMapper.insert(permissionPoint);
        }
    }

    public List<Permission> findByTree(Map<String,Object> map) {
        Set<String> codes = new HashSet<>();
        Set<String> menus = (Set<String>) map.get("menus");
        Set<String> points = (Set<String>) map.get("points");
        codes.addAll(menus);
        codes.addAll(points);
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("code",codes);
        List<Permission> permissions = permissionMapper.selectList(queryWrapper);
        BeanMapUtils.beanToMap(permissions);
        List<Permission> permissionList = TreeUtil.makeTree(permissions, "0");
        return permissionList;
    }
}
