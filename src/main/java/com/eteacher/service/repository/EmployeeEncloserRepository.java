package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEncloserRepository extends JpaRepository<EmployeeEncloser, Long> {
}
