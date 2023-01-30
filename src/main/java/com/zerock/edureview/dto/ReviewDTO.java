package com.zerock.edureview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    //리뷰 번호
    private Long reviewnum;
    //학원 번호
    private Long eno;
    //회원 번호
    private Long mid;
    //회원 닉네임
    private String nickname;
    //회원 이매일
    private String email;
    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;
}
