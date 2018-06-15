package com.rick.shiro.sys.service;

import com.rick.shiro.sys.dao.UserRepository;
import com.rick.shiro.sys.entity.Permission;
import com.rick.shiro.sys.entity.Role;
import com.rick.shiro.sys.entity.User;
//import com.rick.shiro.sys.util.PasswordHelper;
import com.rick.shiro.sys.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rick on 6/15/18.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        PasswordHelper.encryptPassword(user);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void changePassword(Long userId, String newPassword) {

    }

    public Set<String> findRoles(String username) {
        User user = userRepository.findByUsername(username);

        Set<Role> roleSet = user.getRoleSet();

        Set<String> roleString = new HashSet<>();
        for (Role role : roleSet) {
            roleString.add(role.getName());
        }

        return roleString;
    }
    public Set<String> findPermissions(String username) {
        User user = userRepository.findByUsername(username);

        Set<Role> roleSet = user.getRoleSet();

        Set<String> permissionString = new HashSet<>();
        for (Role role : roleSet) {
            Set<Permission> permissionSet = role.getPermissionSet();

            for (Permission permission : permissionSet) {
                permissionString.add(permission.getName());
            }
        }


        return permissionString;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
