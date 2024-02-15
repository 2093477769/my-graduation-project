package com.zb.express.commons.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

    public static String fileHandle(MultipartFile file,String uploadPath) throws IOException {
        // 在这里可以对接收到的图片数据进行处理，例如保存到服务器、进行图片处理等
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf('.');
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + originalFilename.substring(index);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath, fileName);
        Files.write(path, bytes);
        return fileName;
    }
}
