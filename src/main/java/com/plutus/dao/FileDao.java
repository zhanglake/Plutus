package com.plutus.dao;

import com.plutus.entity.MyFile;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/23.
 */
@Repository("fileDao")
public interface FileDao {
    Integer insertFile(MyFile file);

    MyFile findById(Long id);
}
