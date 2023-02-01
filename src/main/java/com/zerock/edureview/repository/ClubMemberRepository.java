package com.zerock.edureview.repository;

import com.zerock.edureview.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
}
