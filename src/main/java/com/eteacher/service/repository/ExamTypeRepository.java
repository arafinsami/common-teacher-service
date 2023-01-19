package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {

    Optional<ExamType> findByExamTypeName(String examTypeName);
}

