
// 2. 修改 BioinformaticsDTO
package com.example.EAT_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BioinformaticsDTO {
    private Long bioinfoId;
    private Long userId;
    private byte[] face; // 使用byte[]存储头像数据
    private String avatarUrl; // 用于返回给前端的头像URL（Base64格式）

    // 构造函数重载
    public BioinformaticsDTO(Long bioinfoId, Long userId, byte[] face) {
        this.bioinfoId = bioinfoId;
        this.userId = userId;
        this.face = face;
    }

    public BioinformaticsDTO(Long bioinfoId, Long userId, String avatarUrl) {
        this.bioinfoId = bioinfoId;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }
}