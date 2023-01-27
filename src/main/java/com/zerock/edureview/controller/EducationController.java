package com.zerock.edureview.controller;

import com.zerock.edureview.dto.EducationDTO;
import com.zerock.edureview.service.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/education")
@RequiredArgsConstructor
@Log4j2
public class EducationController {

    private final EducationService educationService;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(EducationDTO movieDTO, RedirectAttributes redirectAttributes){

        log.info("movieDTO: " + movieDTO);

        Long mno = educationService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/education/list";
    }
}
