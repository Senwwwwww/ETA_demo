// 4. 如果需要，还可以添加文件工具类
package com.example.EAT_demo.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/jpg");
    private static final long MAX_SIZE = 2 * 1024 * 1024; // 2MB

    public static boolean validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        // 检查文件类型
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            return false;
        }

        // 检查文件大小
        if (file.getSize() > MAX_SIZE) {
            return false;
        }

        return true;
    }

    public static String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "";
    }
}