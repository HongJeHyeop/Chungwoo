package com.transport.cw.service;

import com.transport.cw.domain.dtos.BoardDTO;
import com.transport.cw.domain.vos.FileVO;
import com.transport.cw.mappers.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Autowired
    private BoardMapper boardMapper;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "pdf");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //10MB

    private String uploadDirectory = System.getProperty("user.dir")
            + "/src/main/resources/static/uploadImages/";


    public FileVO get_file(int no){
        return boardMapper.get_file(no);
    }
    public List<FileVO> get_files(int no) {
        return boardMapper.get_files(no);
    }
    // DB 파일데이터 삭제
    public boolean delete_file(BoardDTO boardDTO) {
        return boardMapper.delete_file(boardDTO);
    }


    public void insert_board(BoardDTO boardDTO, String id) throws IOException {
        // 작성자 아이디 설정
        boardDTO.setId(id);
        if(boardDTO.getNo() == 0) {
            boardMapper.insert_board(boardDTO);
            boardDTO.setNo(boardMapper.get_last_insert_no());
        }
        // 파일이 비어 있는지 확인
        if(boardDTO.getFile() != null) {
            File dirPath = new File(uploadDirectory, boardDTO.getUuidPath());
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            log.info("만들어진 파일 경로>>>>>>>>>>>>>>>>>>>" + dirPath.toString());

            for (MultipartFile file : boardDTO.getFile()) {

                // 파일 원본이름 추출
                String orgFilename = file.getOriginalFilename();
                // 파일 확장자 추출
                String fileExtension = getFileExtension(orgFilename);
                // 저장될 파일 이름
                String saveFilename = generateUniquePath() + "_" + orgFilename;

                if (!isAllowedExtension(fileExtension)) {
                    throw new IllegalArgumentException("허용되지 않은 파일 확장자 입니다.");
                }
                if (file.getSize() > MAX_FILE_SIZE) {
                    throw new IllegalArgumentException("파일 크기가 제한을 초과하였습니다.");
                }

                file.transferTo(new File(dirPath, saveFilename));
                // 파일 이름, 원본이름, 저장경로 입력
                boardDTO.setFileAddr(dirPath.toString() + File.separator + saveFilename);
                boardDTO.setFileName(saveFilename);
                boardDTO.setFileOrgName(orgFilename);
                log.info("저장할 파일" + boardDTO.getFileAddr());
                boardMapper.insert_file(boardDTO);
            }
        } else {
            log.info("파일이 존재하지 않는 게시물입니다.");
        }
    }

    // 확장자 추출
    private String getFileExtension(String filename) {
        int extensionIndex = filename.lastIndexOf('.');
        if (extensionIndex != -1) {
            return filename.substring(extensionIndex + 1);
        }
        return "";
    }

    // 랜던 문자열 생성
    private String generateUniquePath() {
        String timestemp = String.valueOf(System.currentTimeMillis());
        String randomString = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return timestemp + "_" + randomString;
    }

    // 확장자 검사
    private boolean isAllowedExtension(String extension) {
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }


    /*** quill 이미지 처리 ***/
    public ResponseEntity<String> quill_image_upload(MultipartFile image, String uuidPath) throws IOException {
        if (image.isEmpty()) {
            log.info("quill 업로드할 파일이 존재하지않습니다.");
            return null;
        }
        // 저장할 파일이름 생성
        String fileName = generateUniquePath() + "_" + image.getOriginalFilename();
        // 파일경로 없으면 만들기
        File filePath = new File(uploadDirectory, uuidPath + File.separator +"quillImages");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        log.info("만들어진 파일 경로 >>>" + filePath);
        // 파일저장
        image.transferTo(new File(filePath, fileName));

        // 저장된 이미지의 URL 반환
        String imageUrl = "/uploadImages/" + uuidPath + "/quillImages/" + fileName;
        return ResponseEntity.ok(imageUrl);
    }




}
