package com.example.demo.entity;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadUtil {

    public String saveFile(MultipartFile file) throws IOException {
        // Đường dẫn thư mục lưu trữ tệp tin
        String uploadDir = "src/main/resources/static/image";

        // Tạo thư mục nếu nó không tồn tại
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Tạo tên duy nhất cho tệp tin
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Lưu tệp tin vào thư mục upload
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }
}
