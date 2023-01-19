package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentAdmissionConfigurationRepository extends JpaRepository<StudentAdmissionConfiguration, Long> {

    Optional<StudentAdmissionConfiguration> findByConfigurationName(String configurationName);

    Optional<StudentAdmissionConfiguration> findBySvgId(Long svgId);

    Optional<StudentAdmissionConfiguration> findByClassId(Long classId);

    Optional<StudentAdmissionConfiguration> findByInstituteId(Long instituteId);

    Optional<StudentAdmissionConfiguration> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
