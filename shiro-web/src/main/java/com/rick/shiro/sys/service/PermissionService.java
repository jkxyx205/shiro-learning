package com.rick.shiro.sys.service;

import com.rick.shiro.sys.dao.PermissionRepository;
import com.rick.shiro.sys.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rick on 6/15/18.
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 新增权限
     * @param permission
     */
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    /**
     * 删除权限
     * @param id
     */
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
