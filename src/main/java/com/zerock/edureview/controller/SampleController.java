package com.zerock.edureview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    //로그인을 하지 않아도 접근 할수 있음
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll............................................");
    }
    //로그인 사용자만 접근할 수 있음
    @GetMapping("/member")
    public void exMember(){
        log.info("exMember.........................................");
    }
    //관리자 권한이 있는 사용자만 접근할 수 있음
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin...........................................");
    }
}
