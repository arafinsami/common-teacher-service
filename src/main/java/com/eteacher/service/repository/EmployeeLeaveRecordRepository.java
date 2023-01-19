package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeLeaveRecordRepository extends JpaRepository<EmployeeLeaveRecord, Long> {

  EmployeeLeaveRecord findByEmployeeAndLeaveStartDateAndRecordStatusNot(Employee emp, Date leaveStartDate,
                                                                        RecordStatus recordStatus);

  EmployeeLeaveRecord findByEmployeeAndLeaveEndDateAndRecordStatusNot(Employee emp, Date leaveEndDate,
                                                                      RecordStatus recordStatus);
}
