package com.tgem.ynnk.sys.dao;

import com.tgem.ynnk.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<Permission> findByRoleId(String roleId);
}
