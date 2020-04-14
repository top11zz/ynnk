package com.tgem.ynnk.sys.dao;

import com.tgem.ynnk.sys.entity.Permission;
import com.tgem.ynnk.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Set<Role> findByUserId(String userId);

    Set<Permission> findPermissionByRoleId(String roleId);

    Set<String> findPermissionIdsByRoleId(String roleId);

    int delPersByRoleId(String roleId);

    int insPersByRoleId(@Param("roleId") String roleId,@Param("perId") String perId);
}
