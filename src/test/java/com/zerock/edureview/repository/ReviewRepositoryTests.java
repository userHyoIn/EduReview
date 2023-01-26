package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.Member;
import com.zerock.edureview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

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

    @Test
    public void testGetMovieReviews(){

        Education education = Education.builder().eno(94L).build();

        List<Review> result = reviewRepository.findByEducation(education);

        result.forEach(educationReview -> {

            System.out.print(educationReview.getReviewnum());
            System.out.print("\t" + educationReview.getGrade());
            System.out.print("\t" + educationReview.getText());
            System.out.print("\t" + educationReview.getMember().getEmail());
            System.out.println(" ----------------------------------------------------");
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){

        Long eid = 1L;

        Member member = Member.builder().eid(eid).build();

        //기존
        //memberRepository.deleteById(eid);
        //reviewRepository.deleteByMember(member);

        //순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(eid);
    }
}
