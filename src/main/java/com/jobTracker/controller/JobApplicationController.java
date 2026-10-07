package com.jobTracker.controller;
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
    public JobApplication createApplication(
            @Valid @RequestBody JobApplication jobApplication) {

        return jobApplicationService.createApplication(jobApplication);
    }
    @GetMapping
    public Page<JobApplication> getAllApplications(Pageable pageable) {
        return jobApplicationService.getAllApplications(pageable);
    }
    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        return jobApplicationService.getApplicationById(id);
    }
    @PutMapping("/{id}")
    public JobApplication updateApplication(
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
    public List<JobApplication> getApplicationsByStatus(
            @RequestParam String status) {

        return jobApplicationService.getApplicationsByStatus(status);
    }
}
