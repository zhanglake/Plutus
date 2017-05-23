package com.plutus.controller;

import com.plutus.dto.Result;
import com.plutus.entity.MyFile;
import com.plutus.service.FileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2017/5/22.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadFile(HttpServletRequest request) throws Exception {
        List<Long> insertFileIds = new ArrayList<Long>();
//        InputStream in = new BufferedInputStream(new FileInputStream(url.getFile()));
//        Properties properties = new Properties();
//        properties.load(in);
//        String basePath = properties.getProperty("fileUploadPath");

        ServletContext sc = request.getSession().getServletContext();
        String basePath = sc.getRealPath("/files") + "/";
        File file = new File(basePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(file);// 指定上传文件的临时目录
            factory.setSizeThreshold(1024000);// 指定在内存中缓存数据大小,单位为byte
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10000000);// 指定一次上传多个文件的总尺寸
            List<FileItem> fileItems = new ArrayList<FileItem>();
            try {
                fileItems = upload.parseRequest(request);
            } catch (FileUploadException e1) {
                System.out.println("文件上传发生错误" + e1.getMessage());
            }
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    //文件名
                    String fileName = item.getName();
                    //检查文件后缀格式
                    String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    //创建文件唯一名称
                    String uuid = UUID.randomUUID().toString();
                    //真实上传路径
                    StringBuffer sbRealPath = new StringBuffer();
                    sbRealPath.append(basePath).append(uuid).append(".").append(fileEnd);
                    StringBuffer showPath = new StringBuffer("files/").append(uuid).append(".").append(fileEnd);
                    //写入文件
                    File thisFile = new File(sbRealPath.toString());
                    item.write(thisFile);
                    MyFile myFile = new MyFile();
                    myFile.setFileName(fileName);
                    myFile.setPath(showPath.toString());
                    myFile.setFileType(fileEnd.toLowerCase());
                    fileService.insertFile(myFile);
                    insertFileIds.add(myFile.getId());
                }
            }
        }
        return new Result(200, insertFileIds, Result.SUCCESS);
    }

    @RequestMapping("/query")
    @ResponseBody
    public Result queryFiles(String fileStr) {
        String[] fileStrs = fileStr.split(",");
        List<MyFile> files = new ArrayList<MyFile>();
        for (int i = 0; i < fileStrs.length; i++) {
            MyFile file = fileService.findById(Long.parseLong(fileStrs[i]));
            files.add(file);
        }
        return new Result(200, files, Result.SUCCESS);
    }

}
