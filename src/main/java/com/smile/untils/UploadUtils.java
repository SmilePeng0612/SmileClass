package com.smile.untils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//上传工具类
public class UploadUtils {
    /*
       上传的方法
       返回值为String，因为上传完毕后，要使用文件名
       参数为Part，因为不论是文件名的获取还是写入内容，都需要用到该对象
    */
    public static String uploadFile(Part part) {
        //获取文件名
        String filename = part.getSubmittedFileName();
        //解决同名文件的冲突问题
        filename = UUID.randomUUID() + filename;
        //构建存储上传文件的文件夹
        String dirPath = "/usr/local/softs/apache-tomcat-8.5.56/webapps/saveUploadFile";
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            //将Part中的文件内容写入到文件夹中生成文件
            part.write(dirPath + "/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
