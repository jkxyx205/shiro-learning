package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by rick on 6/15/18.
 *
 * 创建表
 *       users
 *       user_role
 *       roles_permissions
 *
 */
public class JdbcRealmTest {

    @Test
    public void  testLogin() {

        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        Subject subject = SecurityUtils.getSubject();

        //3、得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");


        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        subject.logout();

    }
}
