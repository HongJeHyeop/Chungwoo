package com.transport.cw.domain.dtos;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.FileVO;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Data
@ToString
public class BoardDTO {
    private int no;
    private String boardType;
    private String id;
    private String title;
    private String contents;
    private BoardVO boardVO;
    private String uuidPath;
    private String fileAddr;
    private String fileName;

    private String fileOrgName;
    private List<MultipartFile> file;
}
