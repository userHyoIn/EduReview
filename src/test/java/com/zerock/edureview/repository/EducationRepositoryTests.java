package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.EducationImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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

            System.out.println("==================================================");
        });
    }
}
