package com.plutus.service;

import com.plutus.entity.MyFile;

/**
 * Created by Administrator on 2017/5/23.
 */
public interface FileService {
    Integer insertFile(MyFile file);

    MyFile findById(Long id);
}
