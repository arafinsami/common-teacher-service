package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTrainingDetailsRepository extends JpaRepository<EmployeeTrainingDetail, Long> {
}
