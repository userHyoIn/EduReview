package com.zerock.edureview.service;

import com.zerock.edureview.dto.EducationDTO;
import com.zerock.edureview.dto.PageRequestDTO;
import com.zerock.edureview.dto.PageResultDTO;
import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.EducationImage;
import com.zerock.edureview.repository.EducationImageRepository;
import com.zerock.edureview.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
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

    @Override
    public PageResultDTO<EducationDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("eno").descending());

        Page<Object[]> result = educationRepository.getListPage(pageable);

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });


        Function<Object[], EducationDTO> fn = (arr -> entitiesToDTO(
                (Education)arr[0] ,
                (List<EducationImage>) (Arrays.asList((EducationImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public EducationDTO getEducation(Long eno) {

        List<Object[]> result = educationRepository.getEducationWithAll(eno);

        Education education = (Education) result.get(0)[0];             // Education 엔티티는 가장 앞에 존재 모든 Row가 동일한 값

        List<EducationImage> educationImageList = new ArrayList<>();    // 학원 이미지 개수만큼 EducationImage 객체가 필요

        result.forEach(arr -> {
            EducationImage educationImage = (EducationImage)arr[1];
            educationImageList.add(educationImage);
        });

        Double avg = (Double) result.get(0)[2]; //평균 평점 - 모든 Row가 동일한 값
        Long reviewCnt = (Long) result.get(0)[3]; //리뷰 개수 - 모든 Row가 동일한 값

        return entitiesToDTO(education, educationImageList, avg, reviewCnt);
    }
}
