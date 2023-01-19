package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.RecruitmentType;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitmentTypeRepository extends JpaRepository<RecruitmentType, Long> {

  Optional<RecruitmentType> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

  Optional<RecruitmentType> findByRecruitmentTypeNameAndRecordStatusNot(String recruitmentTypeName, RecordStatus recordStatus);

  Optional<RecruitmentType> findByRecruitmentTypeNameBnAndRecordStatusNot(String recruitmentTypeNameBn, RecordStatus recordStatus);

  Optional<RecruitmentType> findByDescriptionAndRecordStatusNot(String description, RecordStatus recordStatus);

  Optional<RecruitmentType> findByDescriptionBnAndRecordStatusNot(String descriptionBn, RecordStatus recordStatus);
}
