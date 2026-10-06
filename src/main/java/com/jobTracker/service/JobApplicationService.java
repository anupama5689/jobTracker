package com.jobTracker.service;
import com.jobTracker.entity.JobApplication;
import com.jobTracker.exception.ResourceNotFoundException;
import com.jobTracker.repository.JobApplicationRepository;

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
    }public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
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
                jobApplicationRepository.findById(id).orElse(null);

        if (existingApplication == null) {
            return null;
        }

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
        jobApplicationRepository.deleteById(id);
    }
}
