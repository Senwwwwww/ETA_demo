
// 5. 修改 BioinformaticsServiceImpl
package com.example.EAT_demo.service;

import com.example.EAT_demo.dao.BioinformaticsRespository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.BioinformaticsDTO;
import com.example.EAT_demo.pojo.Bioinformatics;
import com.example.EAT_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Arrays;
import java.util.List;

@Service
public class BioinformaticsServiceImpl implements BioinformaticsService {
    @Autowired
    private BioinformaticsRespository bioinformaticsRespository;
    @Autowired
    private UserRepository userRepository;

    // 允许的文件类型
    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/jpg");
    private static final long MAX_SIZE = 2 * 1024 * 1024; // 2MB

    @Override
    public BioinformaticsDTO getBioinformatics(Long userId) throws Exception {
        Bioinformatics bioinformatics = bioinformaticsRespository.findByUserId(userId);
        if (bioinformatics == null) {
            throw new Exception("用户生物信息不存在");
        }

        // 如果有头像数据，转换为 Base64 字符串返回给前端
        String avatarUrl = null;
        if (bioinformatics.getFace() != null && bioinformatics.getFace().length > 0) {
            // 根据文件内容判断MIME类型（简单判断）
            String mimeType = getMimeType(bioinformatics.getFace());
            avatarUrl = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(bioinformatics.getFace());
        }

        return new BioinformaticsDTO(bioinformatics.getBioinfoId(), bioinformatics.getUser().getUserId(), avatarUrl);
    }

    @Override
    public BioinformaticsDTO updateBioinformatics(Long userId, Long bioinfoId, MultipartFile face) throws Exception {
        // 验证文件
        if (!validateFile(face)) {
            throw new Exception("文件格式不正确或大小超过限制");
        }

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new Exception("用户不存在");
        }

        Bioinformatics bioinformatics;

        if (bioinfoId != null) {
            // 更新现有记录
            bioinformatics = bioinformaticsRespository.findById(bioinfoId).orElse(null);
            if (bioinformatics == null) {
                // 如果指定的ID不存在，创建新记录
                bioinformatics = new Bioinformatics();
                bioinformatics.setUser(user);
            }
        } else {
            // 查找是否已有记录
            bioinformatics = bioinformaticsRespository.findByUserId(userId);
            if (bioinformatics == null) {
                // 创建新记录
                bioinformatics = new Bioinformatics();
                bioinformatics.setUser(user);
            }
        }

        try {
            // 将文件转换为字节数组
            byte[] faceBytes = face.getBytes();
            System.out.println(faceBytes.length);
            bioinformatics.setFace(faceBytes);

            // 保存到数据库
            Bioinformatics savedBioinformatics = bioinformaticsRespository.save(bioinformatics);

            // 返回更新后的数据（包含头像URL）
            String mimeType = getMimeType(faceBytes);
            String avatarUrl = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(faceBytes);

            return new BioinformaticsDTO(savedBioinformatics.getBioinfoId(), userId, avatarUrl);

        } catch (IOException e) {
            throw new Exception("文件读取失败", e);
        }
    }

    /**
     * 验证上传的文件
     */
    private boolean validateFile(MultipartFile file) {
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

    /**
     * 根据文件头判断MIME类型
     */
    private String getMimeType(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length < 4) {
            return "image/jpeg"; // 默认
        }

        // 检查文件头
        if (fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8) {
            return "image/jpeg";
        } else if (fileBytes[0] == (byte) 0x89 && fileBytes[1] == (byte) 0x50 &&
                fileBytes[2] == (byte) 0x4E && fileBytes[3] == (byte) 0x47) {
            return "image/png";
        }

        return "image/jpeg"; // 默认
    }
}
