package com.transport.cw.controller;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.service.BoardService;
import com.transport.cw.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;

    @GetMapping("/down/{no}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String no) throws IOException {
        log.info(no);
        BoardVO boardVO = boardService.get_notice(Integer.parseInt(no));
        log.info("다운로드 할 파일" + boardVO);
        Path filePath = Paths.get(boardVO.getFileAddr());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath.toString()));
        log.info("파일경로 : " + filePath);

        String fileName = boardVO.getFileName().substring(boardVO.getFileName().indexOf('_', boardVO.getFileName().indexOf("_") + 1) + 1);
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        String headerValue = "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .headers(headers)
                .body(resource);
    }

    @ResponseBody
    @PostMapping("/quillImage")
    public ResponseEntity<String> quill_image_upload(@RequestParam("image") MultipartFile image) {
        try {
            log.info("quill image controller");
            return fileService.quill_image_upload(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
//        try {
//            // 파일 이름 생성
//            String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "-" + image.getOriginalFilename());
//
//            // 파일 저장 경로 생성
//            Path filePath = Path.of(uploadDirectory, fileName);
//
//            // 파일 저장
//            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//            // 저장된 이미지의 URL 반환
//            String imageUrl = "/uploadImages/" + fileName;
//            return ResponseEntity.ok(imageUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


}
