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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;

    @GetMapping
    public String home() {
        log.info("====== 메인페이지 접속! =====");
        return "home/main";
    }

    // 메인페이지 공지사항 간략히보기
    @ResponseBody
    @GetMapping("mainNotice")
    public List<BoardVO> simple_main_notice() {
        return boardService.simple_main_notice();
    }

    /*** 회사소개페이지 ***/
    @GetMapping("/introduce/greeting")
    public void greeting() {
        log.info("====== 회사 소개 페이지 접속! =======");
    }

    @GetMapping("/introduce/history")
    public void history() {
        log.info("====== 연혁 접속! ======");
    }

    @GetMapping("/introduce/groupChart")
    public void group_chart() {
        log.info("======= 조직도 접속! =========");
    }

    @GetMapping("/introduce/rule")
    public void rule() {
        log.info("======= 기업원칙 접속! =========");
    }

    /*** 지점안내 ***/
    @GetMapping("/branch/branchStatus")
    public void branch_status(@RequestParam String branch, Model model) {
        log.info("========= 지점안내 접속 ==========");
        model.addAttribute("branch", branch);
        switch (branch) {
            case "daegu" :
                model.addAttribute("name", "대구본점");
                model.addAttribute("addr", "대구광역시 수성구 들안로 32길 101");
                model.addAttribute("tel", "053) 753 – 1901 ~ 5");
                model.addAttribute("fax", "053) 756 - 3370 / 752 - 4551");
                break;
            case "gumi" :
                model.addAttribute("name", "구미지점");
                model.addAttribute("addr", "경북 구미시 1공단로 186 - 17 미성빌딩 4층");
                model.addAttribute("tel", "054) 461 - 0993");
                model.addAttribute("fax", "054) 461 - 0393");
                break;
            case "seoul" :
                model.addAttribute("name", "서울지점");
                model.addAttribute("addr", "서울 중구 서소문로 89 순화빌딩 1715호");
                model.addAttribute("tel", "02) 777 - 4060");
                model.addAttribute("fax", "02) 6951 - 0977");
                break;
            case "gyeongsan" :
                model.addAttribute("name", "경산지점");
                model.addAttribute("addr", "경북 경산시 진량읍 공단로 393");
                model.addAttribute("tel", "053) 854 - 6075");
                model.addAttribute("fax", "053) 854 - 2079");
                break;
            case "busan" :
                model.addAttribute("name", "부산지점");
                model.addAttribute("addr", "부산 중구 중앙대로 87 동아일보사옥 9층");
                model.addAttribute("tel", "051) 466 - 6912");
                model.addAttribute("fax", "051) 469 - 0635");
                break;
        }
    }

    /*** 커뮤니티 페이지 ***/
    // 공지사항
    @GetMapping("/community/notice")
    public void notice() {
    }

    // 공지사항 게시글 상세페이지
    @GetMapping("/community/detail")
    public void detail_page(@RequestParam String no, Model model) {
        model.addAttribute("no", no);
    }


    @ResponseBody
    @GetMapping("/community/restDetail")
    public List<Object> rest_detail(
            @RequestParam(defaultValue = "") String no,
            @RequestParam(defaultValue = "") String arrow,
            @RequestParam(defaultValue = "") String boardType
    ) {
        log.info(no + arrow + boardType);
        List<Object> obj = new ArrayList<>();

        log.info("공지사항 상세보기 접속!");
        log.info(boardType);
        if (arrow.equals("next")) {
            BoardVO boardVO = boardService.next_notice(no, boardType);
            obj.add(boardVO);
            obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
            log.info("다음 게시물 클릭! ");
            return obj;
        } else if (arrow.equals("prev")) {
            BoardVO boardVO = boardService.prev_notice(no, boardType);
            obj.add(boardVO);
            obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
            log.info("이전 게시물 클릭! ");
            return obj;
        }
        BoardVO boardVO = boardService.get_notice(no, boardType);
        obj.add(boardVO);
        obj.add(fileService.get_files(String.valueOf(boardVO.getNo()), boardType));
        return obj;
    }


    @ResponseBody
    @GetMapping("/community/notice/list")
    public PagingResponse get_all_notice(
            @RequestParam(defaultValue = "1") int nowPage,
            @RequestParam(defaultValue = "10") int recordSize,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "") String boardType,
            @RequestParam(defaultValue = "") String searchType
    ) {


        log.info("공지사항 목록 접속");
        log.info(boardType);
        return boardService.find_all(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, boardType, searchType));
    }

    @GetMapping("/community/noticeWrite")
    public void notice_write(@RequestParam String no, Model model) {
        if (no == "" || no.equals(null) || no.isEmpty()) {
            model.addAttribute("no", null);
        } else {
            model.addAttribute("no", no);
        }
    }

    @ResponseBody
    @GetMapping("/community/notice/update/write/{no}")
    public BoardVO update_notice_write(@PathVariable String no, @RequestParam String boardType) {
        return boardService.get_notice(no, boardType);
    }


    @PostMapping("/community/insert")
    public String insert_board(
            BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info(userDetails.getUsername());
        log.info("받은 DTO : " + boardDTO);
        try {
            fileService.insert_board(boardDTO, userDetails.getUsername());
        } catch (IOException e) {
            log.info("게시물 작성 실패 오류발생 : " + e);
        }
        return "redirect:/community/notice";
    }


    @PostMapping("/community/notice/update")
    public String update_notice(
            BoardDTO boardDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("게시물 수정 접속 !");
        log.info("받은 DTO" + boardDTO);
        boolean result = boardService.update_notice(boardDTO);
        if (result) {
            log.info("게시물 수정 완료!");
            List<FileVO> fileVOS = fileService.get_files(String.valueOf(boardDTO.getNo()), boardDTO.getBoardType());
            for (FileVO fileVO : fileVOS) {
                File file = new File(fileVO.getFileAddr());
                log.info("삭제할 파일 : " + file.toString());
                if (file.delete()) {
                    log.info("원본 파일삭제 성공[수정하기]");
                } else {
                    log.info("원본 파일삭제 실패");
                }
            }
            if (fileService.delete_file(boardDTO)) {
                log.info("DB 파일데이터 삭제 성공[수정하기]");
            } else {
                log.info("DB 파일데이터 삭제 실패[수정하기]");
            }
            try {
                fileService.insert_board(boardDTO, userDetails.getUsername());
                log.info("수정된 파일 등록 완료!");
            } catch (IOException e) {
                log.info("수정된 파일 등록 실패 : " + e);
            }
        }

        log.info("수정결과 >> " + result);
        return "redirect:/community/notice";
    }

    @ResponseBody
    @DeleteMapping("/community/notice/delete")
    public boolean delete_notice(@RequestBody BoardDTO boardDTO) {
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
            log.info("DB 파일데이터 삭제 성공[삭제하기]");
        } else {
            log.info("DB 파일데이터 삭제 실패[삭제하기]");
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
            log.info("[delete_dir_all] 파일삭제 완료 : " + path.toString());
        } else {
            log.info("[delete_dir_all] 파일삭제 실패 : " + path.toString());
        }
    }


    /*** 고객센터 ***/
    // 온라인 문의
    @GetMapping("/service/inquiryList")
    public void inquiry() {
        log.info("온라인 문의 접속");
    }

}
