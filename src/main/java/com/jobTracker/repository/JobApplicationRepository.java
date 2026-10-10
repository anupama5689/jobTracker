package com.jobTracker.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jobTracker.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(String status);
    List<JobApplication> findByJobType(String jobType);
    Page<JobApplication> findByJobTitleContainingIgnoreCase(String jobTitle, Pageable pageable);
    Page<JobApplication> findByStatusAndJobType(
            String status,
            String jobType,
            Pageable pageable
    );
    Page<JobApplication> findByStatusAndJobTypeAndJobTitleContainingIgnoreCase(
            String status,
            String jobType,
            String jobTitle,
            Pageable pageable
    );
    @Query("""
    SELECT a FROM JobApplication a
    WHERE (:status IS NULL OR a.status = :status)
      AND (:jobType IS NULL OR a.jobType = :jobType)
      AND (:jobTitle IS NULL OR
           LOWER(a.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%')))
    """)
    Page<JobApplication> searchApplications(
            @Param("status") String status,
            @Param("jobType") String jobType,
            @Param("jobTitle") String jobTitle,
            Pageable pageable
    );
}

