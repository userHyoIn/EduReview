package com.zerock.edureview.controller;

import com.zerock.edureview.dto.EducationDTO;
import com.zerock.edureview.dto.PageRequestDTO;
import com.zerock.edureview.service.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String register(EducationDTO educationDTO, RedirectAttributes redirectAttributes){

        log.info("educationDTO: " + educationDTO);

        Long eno = educationService.register(educationDTO);

        redirectAttributes.addFlashAttribute("msg", eno);

        return "redirect:/education/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);

        model.addAttribute("result", educationService.getList(pageRequestDTO));
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long eno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("eno: " + eno);

        EducationDTO educationDTO = educationService.getEducation(eno);

        model.addAttribute("dto", educationDTO);
    }
}
