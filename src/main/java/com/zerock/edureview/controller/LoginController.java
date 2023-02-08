package com.zerock.edureview.controller;

import com.zerock.edureview.security.dto.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Log4j2
@RequestMapping("/Login/")
public class LoginController {

    @GetMapping("/all")
    public String exAll(){

        log.info("exAll..........");

        return "/board/list";
    }
    /*
    @GetMapping("/member")
    public void exMember(){
        log.info("exMember..........");
    }
    */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin..........");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){

        log.info("exMember..........");

        log.info("-------------------------------");
        log.info(clubAuthMember);

    }

    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"user95@gmail.com\"")
    @GetMapping("/exOnly")
    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){

        log.info("exMemberOnly.............");
        log.info(clubAuthMember);

        return "/sample/admin";
    }
}
