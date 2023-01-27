package com.zerock.edureview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
