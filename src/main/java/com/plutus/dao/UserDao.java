package com.plutus.dao;

import com.plutus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/9.
 */
@Repository("userDao")
public interface UserDao extends Mapper {
    User findById(@Param("id") Long id);

    User findByUserNameAndPassword(@Param("userName") String userName);
}
