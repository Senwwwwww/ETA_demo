
// 4. 修改 BioinformaticsService 接口
package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.BioinformaticsDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BioinformaticsService {
    BioinformaticsDTO getBioinformatics(Long userId) throws Exception;
    BioinformaticsDTO updateBioinformatics(Long userId, Long bioinfoId, MultipartFile face) throws Exception;
}