package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryBreakdownRepository extends JpaRepository<SalaryBreakdown, Long> {

    Optional<SalaryBreakdown> findByIdAndRecordStatusNot(Long id, RecordStatus status);

    Optional<SalaryBreakdown> findByBreakdownNameAndRecordStatusNot(String salaryBreakdownName, RecordStatus status);

    Optional<SalaryBreakdown> findByBreakdownNameBnAndRecordStatusNot(String salaryBreakdownNameBn, RecordStatus status);

    Optional<SalaryBreakdown> findTopByOrderByIdDesc();

}
