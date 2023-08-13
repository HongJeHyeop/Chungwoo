package com.transport.cw.controller;

import com.transport.cw.domain.dtos.BoardDTO;
import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.FileVO;
import com.transport.cw.paging.PagingResponse;
import com.transport.cw.service.BoardService;
import com.transport.cw.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/community/repository")
public class RepositoryController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;

    // 자료실
    @GetMapping("/repository")
    public void repository() {
        log.info("자료실 접속");
    }

    @ResponseBody
    @GetMapping("/list")
    public PagingResponse get_all_repository(
            @RequestParam(defaultValue = "1") int nowPage,
            @RequestParam(defaultValue = "10") int recordSize,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "") String boardType,
            @RequestParam(defaultValue = "") String searchType,
            @RequestParam(defaultValue = "") String tradeType
    ) {


        log.info("자료실 목록 접속");
        log.info(boardType);
        return boardService.find_all(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, boardType, searchType, tradeType));
    }


    // 공지사항 게시글 상세페이지
    @GetMapping("/repositoryDetail")
    public void detail_page(@RequestParam String no, Model model) {
        model.addAttribute("no", no);
    }


    @ResponseBody
    @GetMapping("/restDetail")
    public List<Object> rest_detail(
            @RequestParam(defaultValue = "") String no,
            @RequestParam(defaultValue = "") String arrow,
            @RequestParam(defaultValue = "") String boardType
    ) {
        log.info(no + arrow + boardType);
        List<Object> obj = new ArrayList<>();

        log.info("자료실 상세보기 접속!");
        log.info(boardType);
        if (arrow.equals("next")) {
            BoardVO boardVO = boardService.next_notice(no, boardType);
            obj.add(boardVO);
            obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
            log.info("[자료실]다음 게시물 클릭! ");
            return obj;
        } else if (arrow.equals("prev")) {
            BoardVO boardVO = boardService.prev_notice(no, boardType);
            obj.add(boardVO);
            obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
            log.info("[자료실]이전 게시물 클릭! ");
            return obj;
        }
        BoardVO boardVO = boardService.get_notice(no, boardType);
        obj.add(boardVO);
        obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
        return obj;
    }

    @GetMapping("/repositoryWrite")
    public void repository_write(@RequestParam String no, Model model) {
        if (no == "" || no.equals(null) || no.isEmpty()) {
            model.addAttribute("no", null);
        } else {
            model.addAttribute("no", no);
        }
    }

    @ResponseBody
    @GetMapping("/update/write/{no}")
    public BoardVO update_notice_write(@PathVariable String no, @RequestParam String boardType) {
        return boardService.get_notice(no, boardType);
    }


    @PostMapping("/insert")
    public String insert_board(
            BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info(userDetails.getUsername());
        log.info("[자료실]받은 DTO : " + boardDTO);
        try {
            fileService.insert_board(boardDTO, userDetails.getUsername());
        } catch (IOException e) {
            log.info("[자료실]게시물 작성 실패 오류발생 : " + e);
        }
        return "redirect:/community/repository/repository";
    }


    @PostMapping("/update")
    public String update_repository(
            BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("[자료실]게시물 수정 접속 !");
        log.info("[자료실]받은 DTO" + boardDTO);
        boolean result = boardService.update_notice(boardDTO);
        if (result) {
            log.info("[자료실]게시물 수정 완료!");
            List<FileVO> fileVOS = fileService.get_files(String.valueOf(boardDTO.getNo()), boardDTO.getBoardType());
            for (FileVO fileVO : fileVOS) {
                File file = new File(fileVO.getFileAddr());
                log.info("[자료실]삭제할 파일 : " + file.toString());
                if (file.delete()) {
                    log.info("[자료실]원본 파일삭제 성공[수정하기]");
                } else {
                    log.info("[자료실]원본 파일삭제 실패");
                }
            }
            if (fileService.delete_file(boardDTO)) {
                log.info("[자료실]DB 파일데이터 삭제 성공[수정하기]");
            } else {
                log.info("[자료실]DB 파일데이터 삭제 실패[수정하기]");
            }
            try {
                fileService.insert_board(boardDTO, userDetails.getUsername());
                log.info("[자료실]수정된 파일 등록 완료!");
            } catch (IOException e) {
                log.info("[자료실]수정된 파일 등록 실패 : " + e);
            }
        }

        log.info("[자료실]수정결과 >> " + result);
        return "redirect:/community/repository/repository";
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public boolean delete_repository(@RequestBody BoardDTO boardDTO) {
        // 받은 게시물 번호로 삭제할 게시물 조회
        BoardVO boardVO = boardService.get_notice(String.valueOf(boardDTO.getNo()), boardDTO.getBoardType());
        // 삭제할 게시물 파일 디렉토리 경로
        String filePath = System.getProperty("user.dir")
                + "/src/main/resources/static/uploadImages/" + boardVO.getUuidPath();
        File path = new File(filePath);
        // 경로안의 모든 파일 삭제하기
        delete_dir_all(path);

        // DB에 있는 게시물 파일 삭제
        if (fileService.delete_file(boardDTO)) {
            log.info("[자료실]DB 파일데이터 삭제 성공[삭제하기]");
        } else {
            log.info("[자료실]DB 파일데이터 삭제 실패[삭제하기]");
        }
        return boardService.delete_notice(boardDTO);
    }

    // 디렉토리 하위 폴더 및 파일 모두 삭제하기 재귀함수
    public void delete_dir_all(File path) {
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    delete_dir_all(file);
                }
            }
        }
        if (path.delete()) {
            log.info("[자료실][delete_dir_all] 파일삭제 완료 : " + path.toString());
        } else {
            log.info("[자료실][delete_dir_all] 파일삭제 실패 : " + path.toString());
        }
    }

}
