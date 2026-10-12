package com.jobTracker.service;
import com.jobTracker.dto.JobApplicationResponseDTO;
import com.jobTracker.entity.JobApplication;
import com.jobTracker.exception.ResourceNotFoundException;
import com.jobTracker.repository.JobApplicationRepository;
import com.jobTracker.dto.CompanyResponseDTO;
import com.jobTracker.entity.Company;
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
    public JobApplicationResponseDTO createApplication(JobApplication application) {
        JobApplication saved= jobApplicationRepository.save(application);
        return convertToDTO(saved);
    }
    public Page<JobApplicationResponseDTO> getAllApplications(Pageable pageable) {
        return jobApplicationRepository.findAll(pageable).map(this::convertToDTO);
    }
    public JobApplicationResponseDTO getApplicationById(Long id) {

        JobApplication application=jobApplicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job application with id " + id + " not found"
                        )
                );
        return convertToDTO(application);
    }
    public JobApplicationResponseDTO updateApplication(Long id, JobApplication updatedApplication) {

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

        JobApplication saved= jobApplicationRepository.save(existingApplication);
        return convertToDTO(saved);
    }
    public void deleteApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Job application with id " + id + " not found"
            );
        }
        jobApplicationRepository.deleteById(id);
    }
    public List<JobApplicationResponseDTO> getApplicationsByStatus(String status) {
        return jobApplicationRepository.findByStatus(status).stream().map(this::convertToDTO).toList();
    }
    public List<JobApplication> getApplicationsByJobType(String jobType) {
        return jobApplicationRepository.findByJobType(jobType);
    }
    public Page<JobApplicationResponseDTO> searchApplicationsByJobTitle(String jobTitle, Pageable pageable) {
        return jobApplicationRepository.findByJobTitleContainingIgnoreCase(jobTitle, pageable).map(this::convertToDTO);
    }
    public Page<JobApplication> getApplicationsByStatusAndJobType(
            String status,
            String jobType,
            Pageable pageable) {

        return jobApplicationRepository.findByStatusAndJobType(
                status,
                jobType,
                pageable
        );
    }
    public Page<JobApplication> getApplicationsByStatusTypeAndTitle(
            String status,
            String jobType,
            String jobTitle,
            Pageable pageable) {

        return jobApplicationRepository
                .findByStatusAndJobTypeAndJobTitleContainingIgnoreCase(
                        status,
                        jobType,
                        jobTitle,
                        pageable
                );
    }
    public Page<JobApplicationResponseDTO> searchApplications(
            String status,
            String jobType,
            String jobTitle,
            Pageable pageable) {

        return jobApplicationRepository.searchApplications(
                status,
                jobType,
                jobTitle,
                pageable
        ).map(this::convertToDTO);
    }

    private JobApplicationResponseDTO convertToDTO(JobApplication application) {
        Company company = application.getCompany();

        CompanyResponseDTO companyDTO = null;

        if (company != null) {
            companyDTO = new CompanyResponseDTO(
                    company.getId(),
                    company.getName(),
                    company.getWebsite(),
                    company.getLocation(),
                    company.getDescription()
            );
        }

        return new JobApplicationResponseDTO(
                application.getId(),
                application.getJobTitle(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getDeadline(),
                application.getJobType(),
                application.getJobUrl(),
                companyDTO
        );
    }

}
