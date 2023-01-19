package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import com.eteacher.service.entity.composite.SalaryScaleBreakdownPK;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryScaleBreakdownRepository extends JpaRepository<SalaryScaleBreakdown, SalaryScaleBreakdownPK> {

    Optional<SalaryScaleBreakdown> findTopByOrderByRecordIdDesc();

    Optional<SalaryScaleBreakdown> findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(
            SalaryBreakdown salaryBreakdown, SalaryScale salaryScale, RecordStatus status);

    Optional<SalaryScaleBreakdown> findBySalaryBreakdownAndSalaryScale(
            SalaryBreakdown salaryBreakdown, SalaryScale salaryScale);

}
