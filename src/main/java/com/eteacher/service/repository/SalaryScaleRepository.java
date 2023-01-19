package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryScaleRepository extends JpaRepository<SalaryScale, Long> {

  Optional<SalaryScale> findByIdAndRecordStatusNot(Long id, RecordStatus status);

  Optional<SalaryScale> findBySalaryScaleNameAndRecordStatusNot(String salaryScaleName,
                                                                RecordStatus recordStatus);

  Optional<SalaryScale> findBySalaryScaleNameBnAndRecordStatusNot(String salaryScaleNameBn,
                                                                  RecordStatus recordStatus);
}
