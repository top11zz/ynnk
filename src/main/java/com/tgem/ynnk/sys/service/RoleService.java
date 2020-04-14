package com.tgem.ynnk.sys.service;

import com.tgem.ynnk.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
public interface RoleService extends IService<Role> {
   public Set<Role> findByUserId(String userId);

   Role findByRoleId(String id);

   Set<String> findperIdsByRoleId(String id);

   void assignPerms(String roleId,List<String> permIds);
}
