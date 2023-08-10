package com.transport.cw.domain.vos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Log4j2
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class InquiryVO {
    private int no;
    private String name;
    private String phone;
    private String company;
    private String header;
    private String contents;
    @DateTimeFormat(pattern = "yy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime writeDate;
    private int processing;
    private String inquiryType;

    private String tradeType;
}
