package com.zerock.edureview.service;

import com.zerock.edureview.dto.EducationDTO;
import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.EducationImage;
import com.zerock.edureview.repository.EducationImageRepository;
import com.zerock.edureview.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class EducationServiceImpl implements EducationService{

    private final EducationRepository educationRepository;

    private final EducationImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(EducationDTO educationDTO) {

        Map<String, Object> entityMap = dtoToEntity(educationDTO);

        Education education = (Education) entityMap.get("education");

        List<EducationImage> educationImageList = (List<EducationImage>) entityMap.get("imgList");

        educationRepository.save(education);

        educationImageList.forEach(educationImage -> {

            imageRepository.save(educationImage);
        });

        return education.getEno();
    }
}
