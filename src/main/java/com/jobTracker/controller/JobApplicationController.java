package com.jobTracker.controller;
import jakarta.validation.Valid;
import com.jobTracker.entity.JobApplication;
import com.jobTracker.service.JobApplicationService;
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
    public JobApplication createApplication(
            @Valid @RequestBody JobApplication jobApplication) {

        return jobApplicationService.createApplication(jobApplication);
    }
    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
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
    public void deleteApplication(@PathVariable Long id) {
        jobApplicationService.deleteApplication(id);
    }

}
