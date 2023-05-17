package com.transport.cw.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class FileService {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "pdf" );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //10MB

    private String uploadDirectory = System.getProperty("user.dir")
            + "\\src\\main\\resources\\static\\uploadImages\\";


    public List<Object> upload_file(MultipartFile file) throws IOException {

        // 파일이 비어 있는지 확인
        if (file.isEmpty()) {
            throw new IllegalArgumentException("업로드 할 파일이 존재하지 않습니다.");
        }

        String orgFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(orgFilename);
        String saveFilename = generateUniqueFilename(fileExtension, orgFilename);

        if (!isAllowedExtension(fileExtension)) {
            throw new IllegalArgumentException("허용되지 않은 파일 확장자 입니다.");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기가 제한을 초과하였습니다.");
        }
        String randomDirPath = saveFilename.substring(0, saveFilename.indexOf('_', saveFilename.indexOf("_") + 1));
        Path uploadPath = Paths.get(uploadDirectory + randomDirPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        log.info("만들어진 파일 경로>>>>>>>>>>>>>>>>>>>" + uploadPath);

        Path filePath = uploadPath.resolve(saveFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        List<Object> path = Arrays.asList(filePath, saveFilename);
        return path;
    }

    private String getFileExtension(String filename) {
        int extensionIndex = filename.lastIndexOf('.');
        if (extensionIndex != -1) {
            return filename.substring(extensionIndex + 1);
        }
        return "";
    }

    private String generateUniqueFilename(String fileExtension, String orgFilename) {
        String timestemp = String.valueOf(System.currentTimeMillis());
        String randomString = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return timestemp + "_" + randomString + "_" + orgFilename;
    }

    private boolean isAllowedExtension(String extension) {
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }

}
