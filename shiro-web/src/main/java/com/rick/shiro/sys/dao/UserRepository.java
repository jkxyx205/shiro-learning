package com.rick.shiro.sys.dao;

import com.rick.shiro.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rick on 6/15/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
