package com.transport.cw.config;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        // 전달받은 텍스트(text)문자열을 LocalDate형으로 변환하여 반환
        // ofPattern은 원하는 패턴을 작성
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        // LocalDate 객체를 String 형으로 변환하여 반환
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}

