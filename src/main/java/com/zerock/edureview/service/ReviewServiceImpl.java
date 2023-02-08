package com.zerock.edureview.service;

import com.zerock.edureview.dto.BoardDTO;
import com.zerock.edureview.dto.ReviewDTO;
import com.zerock.edureview.entity.Board;
import com.zerock.edureview.entity.Education;
import com.zerock.edureview.entity.Member;
import com.zerock.edureview.entity.Review;
import com.zerock.edureview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfEducation(Long eno) {

        Education education = Education.builder().eno(eno).build();

        List<Review> result = reviewRepository.findByEducation(education);

        return result.stream().map(educationReview -> entityToDto(educationReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO educationReviewDTO) {

        Review educationReview = dtoToEntity(educationReviewDTO);

        reviewRepository.save(educationReview);

        return educationReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO educationReviewDTO) {

        Optional<Review> result = reviewRepository.findById(educationReviewDTO.getReviewnum());

        if(result.isPresent()){

            Review educationReview = result.get();
            educationReview.changeGrade(educationReviewDTO.getGrade());
            educationReview.changeText(educationReviewDTO.getText());

            reviewRepository.save(educationReview);
        }

    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);
    }
}
