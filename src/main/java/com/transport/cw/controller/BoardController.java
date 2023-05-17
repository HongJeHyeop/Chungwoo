package com.transport.cw.controller;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @GetMapping("/down/{no}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String no) throws IOException {
        log.info(no);
        BoardVO boardVO = boardService.get_notice(Integer.parseInt(no));
        log.info("다운로드 할 파일" + boardVO);
        Path filePath = Paths.get(boardVO.getFileAddr());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath.toString()));
        String fileName = boardVO.getFileName();
        log.info("Success download input excel file : " + filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }
}
