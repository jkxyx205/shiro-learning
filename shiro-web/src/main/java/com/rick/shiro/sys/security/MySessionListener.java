package com.rick.shiro.sys.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

/**
 * Created by rick on 6/19/18.
 */
@Component
public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建:" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话过期:" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话停止:" + session.getId());
    }
}
