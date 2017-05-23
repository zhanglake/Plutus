package com.plutus.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/23.
 */
public class MyFile implements Serializable {
    private Long id;
    private String fileName;
    private String path;
    private String fileType;

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {

        return fileType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {

        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }
}
