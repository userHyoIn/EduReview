package com.zerock.edureview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {

    private Long eno;

    private String title;

    @Builder.Default
    private List<EducationImageDTO> imageDTOList = new ArrayList<>();

    //영화의 평균 평점
    private double avg;

    //리뷰 수 jpa의 count( )
    private int reviewCnt;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
