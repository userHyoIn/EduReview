package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.EducationImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class EducationRepositoryTests {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EducationImageRepository educationImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertEducation(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Education education = Education.builder().title("Education..."+i).build();

            System.out.println("---------------------------------------------------");

            educationRepository.save(education);

            int count = (int)(Math.random() * 5) + 1;

            for(int j = 0; j < count; j++){
                EducationImage educationImage = EducationImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .education(education)
                        .imgName("test" +j+ ".jpg").build();

                educationImageRepository.save(educationImage);
            }

            System.out.println("===================================================");
        });
    }

    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"eno"));

        Page<Object[]> result = educationRepository.getListPage(pageRequest);

        for(Object[] objects: result.getContent()){

            System.out.println(Arrays.toString(objects));
        }
    }


    @Test
    public void testGetEducationWithAll(){

        List<Object[]> result = educationRepository.getEducationWithAll(94L);

        System.out.println(result);

        for (Object[] arr : result){

            System.out.println(Arrays.toString(arr));
        }
    }
}
