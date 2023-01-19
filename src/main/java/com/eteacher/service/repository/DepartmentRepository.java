package com.eteacher.service.repository;

import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    Optional<Department> findByDepartmentName(String departmentName);

    @Query("select exams from Department d join d.examinations exams where exams.year = :year")
    Optional<DepartmentalExamination> findByExamYear(Integer year);

    @Query("SELECT d FROM Department d WHERE " +
            "d.departmentName LIKE CONCAT('%', :departmentName, '%')"
    )
    Page<Department> searchDepartment(String departmentName, Pageable pageable);

}
