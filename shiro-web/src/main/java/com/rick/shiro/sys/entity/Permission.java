package com.rick.shiro.sys.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rick on 6/15/18.
 */
@Entity
@Table(name = "sys_permission")
public class Permission extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
