// 1. 修改 BioinformaticsController
package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.dto.BioinformaticsDTO;
import com.example.EAT_demo.service.BioinformaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BioinformaticsController {
    @Autowired
    BioinformaticsService bioinformaticsService;

    @GetMapping("/user/getBioinformatics/{userId}")
    @CrossOrigin
    public Response<BioinformaticsDTO> getUserBioinformatics(@PathVariable Long userId) throws Exception {
        return Response.newSuccess(bioinformaticsService.getBioinformatics(userId));
    }

    @PostMapping("/user/updateBioinformatics")
    @CrossOrigin
    @RecordBehavior(value = "更新头像")
    public Response<BioinformaticsDTO> updateBioinformatics(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "bioinfoId", required = false) Long bioinfoId,
            @RequestParam("face") MultipartFile face) {
        try {
            BioinformaticsDTO result = bioinformaticsService.updateBioinformatics(userId, bioinfoId, face);
            return Response.newSuccess(result);
        } catch (Exception e) {
            return Response.newFail("更新失败: " + e.getMessage());
        }
    }
}