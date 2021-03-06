package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by rick on 6/15/18.
 *
 *
 */
public class PasswordTest {

    @Test
    public void  testLogin() {

        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro-passwordservice.ini");

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

    public static void main(String[] args) {
        DefaultPasswordService passwordService = new DefaultPasswordService();

        System.out.println(passwordService.encryptPassword("123"));

        //output: $shiro1$SHA-256$500000$VYJGkGlzDu6PKLjg/ma+Uw==$ywJvjEagvsK1WuQZpEKZnLmyeNkpDFi5ThCRpN1NQR0=
    }
}
