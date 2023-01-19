package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillsRepository extends JpaRepository<EmployeeLanguageSkill, Long> {
}
