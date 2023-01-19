package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteExamMarkingPolicyDetailRepository extends JpaRepository<InstituteExamMarkingPolicyDetail, Long> {
}
