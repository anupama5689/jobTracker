package com.jobTracker.service;
import com.jobTracker.entity.JobApplication;
import com.jobTracker.exception.ResourceNotFoundException;
import com.jobTracker.repository.JobApplicationRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
    public JobApplication createApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }
    public Page<JobApplication> getAllApplications(Pageable pageable) {
        return jobApplicationRepository.findAll(pageable);
    }
    public JobApplication getApplicationById(Long id) {

        return jobApplicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job application with id " + id + " not found"
                        )
                );
    }
    public JobApplication updateApplication(Long id, JobApplication updatedApplication) {

        JobApplication existingApplication =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Job application with id " + id + " not found"
                                )
                        );

        existingApplication.setJobTitle(updatedApplication.getJobTitle());
        existingApplication.setStatus(updatedApplication.getStatus());
        existingApplication.setApplicationDate(updatedApplication.getApplicationDate());
        existingApplication.setDeadline(updatedApplication.getDeadline());
        existingApplication.setJobType(updatedApplication.getJobType());
        existingApplication.setJobUrl(updatedApplication.getJobUrl());
        existingApplication.setCompany(updatedApplication.getCompany());

        return jobApplicationRepository.save(existingApplication);
    }
    public void deleteApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Job application with id " + id + " not found"
            );
        }
        jobApplicationRepository.deleteById(id);
    }
    public List<JobApplication> getApplicationsByStatus(String status) {
        return jobApplicationRepository.findByStatus(status);
    }
    public List<JobApplication> getApplicationsByJobType(String jobType) {
        return jobApplicationRepository.findByJobType(jobType);
    }
    public Page<JobApplication> searchApplicationsByJobTitle(String jobTitle, Pageable pageable) {
        return jobApplicationRepository.findByJobTitleContainingIgnoreCase(jobTitle, pageable);
    }
}
