package com.example.EAT_demo.controller;


import com.example.EAT_demo.QR.QRCodeParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/qrcode")
@CrossOrigin(origins = "*")
public class QRCodeParserController {

    /**
     * 解析上传的二维码图片
     */
    @PostMapping("/parse")
    public ResponseEntity<Map<String, Object>> parseQRCode(@RequestParam("image") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 验证文件
            if (file.isEmpty()) {
                response.put("success", false);
                response.put("message", "请选择要上传的文件");
                return ResponseEntity.badRequest().body(response);
            }

            // 验证文件类型
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                response.put("success", false);
                response.put("message", "请上传图片文件");
                return ResponseEntity.badRequest().body(response);
            }

            // 验证文件大小 (限制为5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                response.put("success", false);
                response.put("message", "文件大小不能超过5MB");
                return ResponseEntity.badRequest().body(response);
            }

            // 解析二维码
            String qrContent = QRCodeParser.parseQRCode(file.getInputStream());

            if (qrContent != null && !qrContent.trim().isEmpty()) {
                response.put("success", true);
                response.put("content", qrContent);
                response.put("message", "二维码解析成功");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "未能识别到二维码内容");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "二维码解析失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * 批量解析二维码（支持多个文件）
     */
    @PostMapping("/parse/batch")
    public ResponseEntity<Map<String, Object>> parseQRCodeBatch(@RequestParam("images") MultipartFile[] files) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (files.length == 0) {
                response.put("success", false);
                response.put("message", "请选择要上传的文件");
                return ResponseEntity.badRequest().body(response);
            }

            Map<String, Object> results = new HashMap<>();
            int successCount = 0;
            int failCount = 0;

            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                Map<String, Object> fileResult = new HashMap<>();

                try {
                    if (!file.isEmpty() && file.getContentType() != null &&
                            file.getContentType().startsWith("image/")) {

                        String qrContent = QRCodeParser.parseQRCode(file.getInputStream());

                        if (qrContent != null && !qrContent.trim().isEmpty()) {
                            fileResult.put("success", true);
                            fileResult.put("content", qrContent);
                            fileResult.put("message", "解析成功");
                            successCount++;
                        } else {
                            fileResult.put("success", false);
                            fileResult.put("message", "未能识别到二维码内容");
                            failCount++;
                        }
                    } else {
                        fileResult.put("success", false);
                        fileResult.put("message", "文件格式不支持");
                        failCount++;
                    }
                } catch (Exception e) {
                    fileResult.put("success", false);
                    fileResult.put("message", "解析失败: " + e.getMessage());
                    failCount++;
                }

                results.put("file_" + i, fileResult);
            }

            response.put("success", true);
            response.put("results", results);
            response.put("summary", Map.of(
                    "total", files.length,
                    "success", successCount,
                    "failed", failCount
            ));
            response.put("message", String.format("批量解析完成，成功：%d，失败：%d", successCount, failCount));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "批量解析失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}