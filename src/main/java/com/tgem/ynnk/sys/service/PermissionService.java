package com.tgem.ynnk.sys.service;

        import com.tgem.ynnk.sys.entity.Permission;
        import com.baomidou.mybatisplus.extension.service.IService;
        import com.tgem.ynnk.sys.entity.PermissionMenu;
        import com.tgem.ynnk.sys.entity.PermissionPoint;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Map;
        import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
public interface PermissionService extends IService<Permission> {
    Set<Permission> findByRoleId(String roleId);

    void add(Permission permission, PermissionMenu permissionMenu, PermissionPoint permissionPoint) throws Exception;

    List<Permission> findByTree(Map<String,Object> map);
}
