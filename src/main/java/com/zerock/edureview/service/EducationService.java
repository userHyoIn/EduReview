package com.zerock.edureview.service;

import com.zerock.edureview.dto.EducationDTO;
import com.zerock.edureview.dto.EducationImageDTO;
import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.EducationImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EducationService {

    Long register(EducationDTO educationDTO);

    default Map<String, Object>dtoToEntity(EducationDTO educationDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Education education = Education.builder()
                .eno(educationDTO.getEno())
                .title(educationDTO.getTitle())
                .build();

        entityMap.put("education", education);

        List<EducationImageDTO> imageDTOList = educationDTO.getImageDTOList();

        //EducationImageDTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0){

            List<EducationImage> educationImageList = imageDTOList.stream().map(educationImageDTO -> {
                EducationImage educationImage = EducationImage.builder()
                        .path(educationImageDTO.getPath())
                        .imgName(educationImageDTO.getImgName())
                        .uuid(educationImageDTO.getUuid())
                        .education(education)
                        .build();

                return educationImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", educationImageList);
        }

        return entityMap;
    }
}
