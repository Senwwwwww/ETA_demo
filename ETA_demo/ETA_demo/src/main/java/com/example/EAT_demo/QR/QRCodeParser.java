
package com.example.EAT_demo.QR;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码解析工具类
 */
public class QRCodeParser {

    /**
     * 从输入流中解析二维码
     * @param inputStream 图片输入流
     * @return 二维码内容，解析失败返回null
     * @throws IOException IO异常
     */
    public static String parseQRCode(InputStream inputStream) throws IOException {
        try {
            // 读取图片
            BufferedImage image = ImageIO.read(inputStream);
            if (image == null) {
                throw new IOException("无法读取图片文件");
            }

            return parseQRCode(image);

        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("二维码解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从BufferedImage中解析二维码
     * @param image 图片对象
     * @return 二维码内容，解析失败返回null
     */
    public static String parseQRCode(BufferedImage image) {
        try {
            // 创建LuminanceSource
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // 设置解码参数
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);

            // 使用多重二维码读取器
            QRCodeMultiReader multiReader = new QRCodeMultiReader();

            try {
                // 尝试解码
                Result result = multiReader.decode(bitmap, hints);
                return result.getText();
            } catch (NotFoundException e) {
                // 如果多重读取器失败，尝试单个读取器
                MultiFormatReader reader = new MultiFormatReader();
                reader.setHints(hints);
                Result result = reader.decode(bitmap);
                return result.getText();
            }

        } catch (Exception e) {
            System.err.println("二维码解析失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 尝试多种方式解析二维码（增强版）
     * @param image 图片对象
     * @return 二维码内容，解析失败返回null
     */
    public static String parseQRCodeEnhanced(BufferedImage image) {
        // 尝试原图解析
        String result = parseQRCode(image);
        if (result != null) {
            return result;
        }

        try {
            // 尝试增强对比度后解析
            BufferedImage enhancedImage = enhanceContrast(image);
            result = parseQRCode(enhancedImage);
            if (result != null) {
                return result;
            }

            // 尝试转换为灰度图后解析
            BufferedImage grayImage = convertToGrayscale(image);
            result = parseQRCode(grayImage);
            if (result != null) {
                return result;
            }

            // 尝试旋转图片后解析
            for (int angle = 90; angle < 360; angle += 90) {
                BufferedImage rotatedImage = rotateImage(image, angle);
                result = parseQRCode(rotatedImage);
                if (result != null) {
                    return result;
                }
            }

        } catch (Exception e) {
            System.err.println("增强解析失败: " + e.getMessage());
        }

        return null;
    }

    /**
     * 增强图片对比度
     */
    private static BufferedImage enhanceContrast(BufferedImage image) {
        BufferedImage enhanced = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // 增强对比度
                r = Math.min(255, Math.max(0, (int) ((r - 128) * 1.5 + 128)));
                g = Math.min(255, Math.max(0, (int) ((g - 128) * 1.5 + 128)));
                b = Math.min(255, Math.max(0, (int) ((b - 128) * 1.5 + 128)));

                enhanced.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }

        return enhanced;
    }

    /**
     * 转换为灰度图
     */
    private static BufferedImage convertToGrayscale(BufferedImage image) {
        BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // 计算灰度值
                int grayValue = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                gray.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
            }
        }

        return gray;
    }

    /**
     * 旋转图片
     */
    private static BufferedImage rotateImage(BufferedImage image, int angle) {
        double radian = Math.toRadians(angle);
        double cos = Math.abs(Math.cos(radian));
        double sin = Math.abs(Math.sin(radian));

        int width = image.getWidth();
        int height = image.getHeight();

        int newWidth = (int) (width * cos + height * sin);
        int newHeight = (int) (height * cos + width * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, image.getType());

        java.awt.Graphics2D g2d = rotated.createGraphics();
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(radian, width / 2, height / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotated;
    }
}