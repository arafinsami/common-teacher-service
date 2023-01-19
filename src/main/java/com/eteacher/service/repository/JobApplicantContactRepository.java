package com.eteacher.service.repository;

import com.eteacher.service.entity.job.JobApplicantContact;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicantContactRepository extends JpaRepository<JobApplicantContact, Long> {
}
