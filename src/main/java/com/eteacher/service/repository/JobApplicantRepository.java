package com.eteacher.service.repository;

import com.eteacher.service.entity.job.JobApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicantRepository extends JpaRepository<JobApplicant, Long> {

    Optional<JobApplicant> findByPassportNo(String passportNo);
}
