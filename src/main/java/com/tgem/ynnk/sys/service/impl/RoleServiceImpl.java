package com.tgem.ynnk.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgem.ynnk.sys.dao.RoleMapper;
import com.tgem.ynnk.sys.entity.Permission;
import com.tgem.ynnk.sys.entity.Role;
import com.tgem.ynnk.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Set<Role> findByUserId(String userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public Set<String> findperIdsByRoleId(String id) {
        Set<String> perIds = roleMapper.findPermissionIdsByRoleId(id);
        return perIds;
    }

    @Override
    @Transactional
    public void assignPerms(String id, List<String> permIds) {
        roleMapper.delPersByRoleId(id);
        permIds.forEach(x->{
            roleMapper.insPersByRoleId(id,x);
        });
    }

    @Override
    public Role findByRoleId(String id) {
        Role role = roleMapper.selectById(id);
        Set<Permission> permissions = roleMapper.findPermissionByRoleId(id);
        role.setPermissions(permissions);
        return role;
    }
}
