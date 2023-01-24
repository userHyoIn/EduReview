package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
