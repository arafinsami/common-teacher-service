package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.ExamTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamTermRepository extends JpaRepository<ExamTerm, Long> {

    Optional<ExamTerm> findByExamTermName(String examTermName);

    Optional<ExamTerm> findByExamTermNameBn(String examTermNameBn);
}
