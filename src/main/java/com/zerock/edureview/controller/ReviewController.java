package com.zerock.edureview.controller;

import com.zerock.edureview.dto.ReviewDTO;
import com.zerock.edureview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("eno") Long eno){

        log.info("list ------------------------------------------------------");
        log.info("Eno: " + eno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfEducation(eno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{eno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO educationReviewDTO){

        log.info("add EducationReview ---------------------------------------");
        log.info("reviewDTO: " + educationReviewDTO);

        Long reviewnum = reviewService.register(educationReviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{eno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO educationReviewDTO){

        log.info("modify EducationReview ------------------------------------");
        log.info("reviewDTO: " + educationReviewDTO);

        reviewService.modify(educationReviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{eno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){

        log.info("removeReview-----------------------------------------------");
        log.info("reviewnum: " + reviewnum);

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }
}
