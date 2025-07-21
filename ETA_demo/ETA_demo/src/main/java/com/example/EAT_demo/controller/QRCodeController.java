package com.example.EAT_demo.controller;

import com.example.EAT_demo.QR.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class QRCodeController {

    @Value("${app.base-url:http://10.100.164.82:8080}")
    private String baseUrl;

    @GetMapping(value = "/qrcode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCode(@PathVariable String id) {
        try {
            // 构造携带ID的URL
            String targetUrl = baseUrl + "/items?id=" + id;

            // 生成二维码图片
            BufferedImage qrImage = QRCodeGenerator.generateQRCode(targetUrl, 300, 300);

            // 转换为字节数组返回
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", baos);
            return ResponseEntity.ok(baos.toByteArray());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/qrcode/generate")
    public ResponseEntity<?> generateQRCode(@RequestBody Map<String, String> request) {
        try {
            String content = request.get("content");
            if (content == null || content.trim().isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("message", "Content cannot be empty");
                return ResponseEntity.badRequest().body(error);
            }

            BufferedImage qrImage = QRCodeGenerator.generateQRCode(content, 300, 300);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", baos);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "QR code generated successfully");
            response.put("data", java.util.Base64.getEncoder().encodeToString(baos.toByteArray()));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error generating QR code: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
}