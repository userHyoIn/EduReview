package com.zerock.edureview.repository;

import com.zerock.edureview.entity.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query("select e, ei, avg(coalesce(r.grade,0)),  count(r) from Education e " +
            "left outer join EducationImage ei on ei.education = e " +
            "left outer join Review  r on r.education = e group by e ")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select e, ei, avg(coalesce(r.grade,0)),  count(r) from Education e " +
            "left outer join EducationImage ei on ei.education = e " +
            "left outer join Review  r on r.education = e " +
            "where e.eno = :eno group by ei")
    List<Object[]> getEducationWithAll(Long eno);
}
