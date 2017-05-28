package com.plutus.service;

import com.plutus.dao.DataSourceSqlSessionFactory;
import com.plutus.dao.MapperFactory;
import com.plutus.dao.UserDao;
import com.plutus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/5/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired(required = false)
//    private UserDao userDao;



    @Override
    public User findById(Long id) {
//        User user = userDao.findById(id);
//        return user;
        return null;
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        UserDao userDao = MapperFactory.createMapper(UserDao.class, DataSourceSqlSessionFactory.DataSourceEnvironment.DEVELOPMENT);
        User user = userDao.findByUserNameAndPassword(userName);
        if (null != user) {
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return user;
//        return null;
    }

}
