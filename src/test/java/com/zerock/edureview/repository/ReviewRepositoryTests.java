package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.Member;
import com.zerock.edureview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertEducationReviews(){

        IntStream.rangeClosed(1,200).forEach(i -> {

            //movie num
            Long eno = (long)(Math.random()*100) + 1;

            //review num
            Long eid = ((long)(Math.random()*100) + 1);

            Member member = Member.builder().eid(eid).build();

            Review educationReview = Review.builder()
                    .member(member)
                    .education(Education.builder().eno(eno).build())
                    .grade((int)(Math.random()*5) + 1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(educationReview);
        });
    }
}
