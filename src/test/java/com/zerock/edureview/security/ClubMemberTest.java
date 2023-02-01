package com.zerock.edureview.security;

import com.zerock.edureview.entity.ClubMember;
import com.zerock.edureview.entity.ClubMemberRole;
import com.zerock.edureview.repository.ClubMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTest {

    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummines(){

        //1 ~ 80 USER 지정
        //81 ~ 90 USER, MANAGER
        //91 ~ 100 USER, MANAGER, ADMIN
        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user"+i+"@gmail.com")
                    .name("user"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            clubMember.addMemberRole(ClubMemberRole.USER);

            if(i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }

            if(i > 90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            repository.save(clubMember);
        });
    }
}
