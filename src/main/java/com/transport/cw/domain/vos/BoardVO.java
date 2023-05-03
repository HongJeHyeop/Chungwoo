package com.transport.cw.domain.vos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Log4j2
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class BoardVO {
    private int no;
    private String boardType;
    private String id;
    private String title;
    private String contents;
    private String fileAddr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime writeDate;

}
