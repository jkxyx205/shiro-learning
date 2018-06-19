package com.rick.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by rick on 6/15/18.
 */
public class MyRealm extends AuthorizingRealm {

    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public String getName() {
        return "myrealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

//        if(!"zhang".equals(username)) {
//            throw new UnknownAccountException(); //如果用户名错误
//        }
//
//        if(!"123".equals(password)) {
//            throw new IncorrectCredentialsException(); //如果密码错误
//        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现;
        return new SimpleAuthenticationInfo("zhang",
                    "123",
                    getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
