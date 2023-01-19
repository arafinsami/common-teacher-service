package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeSalaryIncrementRepository extends JpaRepository<EmployeeSalaryIncrement, Long> {

  EmployeeSalaryIncrement findByEmployeeAndEffectiveDateAndRecordStatusNot(Employee emp, Date effectiveDate,
                                                                           RecordStatus recordStatus);
}
