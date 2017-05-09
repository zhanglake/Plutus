package com.plutus.service;

import com.plutus.entity.User;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface UserService {
    public User findById(Long id);

    public User findByUserNameAndPassword(String userName, String password);
}
