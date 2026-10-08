package com.jobTracker.repository;

import com.jobTracker.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(String status);
    List<JobApplication> findByJobType(String jobType);
    Page<JobApplication> findByJobTitleContainingIgnoreCase(String jobTitle, Pageable pageable);
}

