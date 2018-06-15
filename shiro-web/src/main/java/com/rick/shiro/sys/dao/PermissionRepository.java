package com.rick.shiro.sys.dao;

import com.rick.shiro.sys.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rick on 6/15/18.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
