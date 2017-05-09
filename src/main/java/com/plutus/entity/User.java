package com.plutus.entity;

/**
 * Created by zhanglake on 2017/5/9.
 */
public class User {
    private Long id;
    private String userName;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
