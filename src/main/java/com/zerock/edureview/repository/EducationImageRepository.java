package com.zerock.edureview.repository;

import com.zerock.edureview.entity.EducationImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationImageRepository extends JpaRepository<EducationImage, Long> {
}
