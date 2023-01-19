package com.eteacher.service.repository;

import com.eteacher.service.entity.job.JobCircular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCircularRepository extends JpaRepository<JobCircular, Long> {
}
