package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by rick on 6/15/18.
 */
public class PermissionTest {

    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();

        //判断拥有权限:user:create
         Assert.assertTrue(subject.isPermitted("user:create"));

         //判断拥有权限:user:update and user:delete
         Assert.assertTrue(subject.isPermittedAll("user:create", "user:delete"));

         //判断没有权限:user:view
         Assert.assertFalse(subject.isPermitted("user:view"));


    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        login("classpath:shiro-permission.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        //断言拥有权限:user:create
         subject.checkPermission("user:create");
        // 断言拥有权限:user:delete and user:update
         subject.checkPermissions("user:delete", "user:update");
        // 断言拥有权限:user:view 失败抛出异常
         subject.checkPermissions("user:view");
    }


    private void login(String configFile, String username, String password) {

        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        Subject subject = SecurityUtils.getSubject();

        //3、得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);


        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            throw e;
        }
    }


}
