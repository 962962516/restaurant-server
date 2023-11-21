package com.restaurant.project.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class UploadPicture {
    public static String uploadImage(String uploadDir, MultipartFile file){
        //确保目录存在，如果不存在则创建
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 生成唯一的文件名
            String uniqueFileName = UUID.randomUUID() + "_" + fileName;

            // 构建文件路径
            String filePath = uploadDir + uniqueFileName;

            // 将文件保存到项目的images目录中
            Path path = Paths.get(filePath);
            file.transferTo(path);

            // 生成文件的访问URL
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(uniqueFileName)
                    .toUriString();

            return "文件上传成功！文件访问URL：" + fileUrl;
        } catch (
                IOException e) {
            return "文件上传失败: " + e.getMessage();
        }
    }

}
