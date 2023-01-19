package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    Optional<EmployeeAttendance> findTopByOrderByIdDesc();

    Optional<EmployeeAttendance> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    @Query("SELECT ea FROM EmployeeAttendance ea WHERE " +
            "(:employee IS NULL OR ea.employee.id = :employee) AND " +
            "(:department IS NULL OR ea.employee.department.id = :department) AND " +
            "(:today IS NULL OR ea.dateOfAttendance = :today) AND " +
            "((:startDate IS NULL AND :endDate IS NULL) OR ea.dateOfAttendance BETWEEN :startDate AND :endDate)")
    Page<EmployeeAttendance> searchEmployeeAttendance(Long employee, Long department, Date today, Date startDate, Date endDate, Pageable pageable);
}
