package com.plutus.service;

import com.plutus.dao.FileDao;
import com.plutus.entity.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/23.
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public Integer insertFile(MyFile file) {
        return fileDao.insertFile(file);
    }

    @Override
    public MyFile findById(Long id) {
        return fileDao.findById(id);
    }

}
