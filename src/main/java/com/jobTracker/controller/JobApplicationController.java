package com.jobTracker.controller;
import com.jobTracker.dto.JobApplicationResponseDTO;
import jakarta.validation.Valid;
import com.jobTracker.entity.JobApplication;
import com.jobTracker.service.JobApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplicationResponseDTO createApplication(
            @Valid @RequestBody JobApplication jobApplication) {

        return jobApplicationService.createApplication(jobApplication);
    }
    @GetMapping
    public Page<JobApplicationResponseDTO> getAllApplications(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) String jobTitle,
            Pageable pageable) {

        return jobApplicationService.searchApplications(
                status,
                jobType,
                jobTitle,
                pageable
        );
    }
    @GetMapping("/{id}")
    public JobApplicationResponseDTO getApplicationById(@PathVariable Long id) {
        return jobApplicationService.getApplicationById(id);
    }
    @PutMapping("/{id}")
    public JobApplicationResponseDTO updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplication updatedApplication) {

        return jobApplicationService.updateApplication(id, updatedApplication);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable Long id) {
        jobApplicationService.deleteApplication(id);
    }
    @GetMapping("/filter")
    public List<JobApplicationResponseDTO> getApplicationsByStatus(
            @RequestParam String status) {

        return jobApplicationService.getApplicationsByStatus(status);
    }
    @GetMapping("/filter/job-type")
    public List<JobApplicationResponseDTO> getApplicationsByJobType(
            @RequestParam String jobType) {

        return jobApplicationService.getApplicationsByJobType(jobType);
    }
    @GetMapping("/search")
    public Page<JobApplicationResponseDTO> searchApplicationsByJobTitle(
            @RequestParam String jobTitle,Pageable pageable) {

        return jobApplicationService.searchApplicationsByJobTitle(jobTitle,pageable);
    }
    @GetMapping("/filter/status-and-type")
    public Page<JobApplicationResponseDTO> getApplicationsByStatusAndJobType(
            @RequestParam String status,
            @RequestParam String jobType,
            Pageable pageable) {

        return jobApplicationService.getApplicationsByStatusAndJobType(
                status,
                jobType,
                pageable
        );
    }
    @GetMapping("/filter/advanced")
    public Page<JobApplicationResponseDTO> getApplicationsByStatusTypeAndTitle(
            @RequestParam String status,
            @RequestParam String jobType,
            @RequestParam String jobTitle,
            Pageable pageable) {

        return jobApplicationService.getApplicationsByStatusTypeAndTitle(
                status,
                jobType,
                jobTitle,
                pageable
        );
    }
}
