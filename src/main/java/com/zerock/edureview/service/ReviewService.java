package com.zerock.edureview.service;

import com.zerock.edureview.dto.ReviewDTO;
import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.Member;
import com.zerock.edureview.entity.Review;

import java.util.List;

public interface ReviewService {

    //학원의 모든 리뷰를 가져온다.
    List<ReviewDTO> getListOfEducation(Long eno);
    //리뷰 추가
    Long register(ReviewDTO educationReviewDTO);
    //특정 리뷰 수정
    void modify(ReviewDTO educationReviewDTO);
    //리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO educationReviewDTO){

        Review educationReview = Review.builder()
                .reviewnum(educationReviewDTO.getReviewnum())
                .education(Education.builder().eno(educationReviewDTO.getEno()).build())
                .member(Member.builder().email(educationReviewDTO.getEmail()).build())
                .grade(educationReviewDTO.getGrade())
                .text(educationReviewDTO.getText())
                .build();

        return educationReview;
    }

    default ReviewDTO entityToDto(Review educationReview){

        ReviewDTO educationDTO = ReviewDTO.builder()
                .reviewnum(educationReview.getReviewnum())
                .eno(educationReview.getEducation().getEno())
                //.mid(educationReview.getMember().getMid())
                .nickname(educationReview.getMember().getNickname())
                .email(educationReview.getMember().getEmail())
                .grade(educationReview.getGrade())
                .text(educationReview.getText())
                .regDate(educationReview.getRegDate())
                .modDate(educationReview.getModDate())
                .build();

        return educationDTO;
    }
}
