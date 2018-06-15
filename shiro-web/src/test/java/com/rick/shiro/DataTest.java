package com.rick.shiro;

import com.rick.shiro.sys.entity.BaseEntity;
import com.rick.shiro.sys.entity.Permission;
import com.rick.shiro.sys.entity.Role;
import com.rick.shiro.sys.entity.User;
import com.rick.shiro.sys.service.PermissionService;
import com.rick.shiro.sys.service.RoleService;
import com.rick.shiro.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by rick on 6/15/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testPermissionAdd() {
        Permission permission = new Permission();

        permission.setName("user:create");
        permission.setStatus(BaseEntity.StatusEnum.NORMAL);

        permissionService.save(permission);

    }

    @Test
    public void testRoleAdd() {
        Role role = new Role();
        role.setStatus(BaseEntity.StatusEnum.NORMAL);
        role.setName("admin");


        Permission permission = new Permission();
        permission.setId(1L);
        role.getPermissionSet().add(permission);

        roleService.save(role);
    }

    @Test
    public void testUserAdd() {
        User user = new User();
        user.setStatus(BaseEntity.StatusEnum.NORMAL);
        user.setPassword("jkxxy205");
        user.setUsername("rick");

        Role role = new Role();
        role.setId(1L);

        user.getRoleSet().add(role);
        userService.save(user);
    }

    @Test
    public void testUserSubject() {
        System.out.println(userService.findPermissions("rick"));
        System.out.println(userService.findRoles("rick"));

    }


}
