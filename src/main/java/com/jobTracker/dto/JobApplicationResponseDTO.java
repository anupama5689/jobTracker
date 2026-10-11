package com.jobTracker.dto;
import java.time.LocalDate;

public class JobApplicationResponseDTO { private Long id;
    private String jobTitle;
    private String status;
    private LocalDate applicationDate;
    private LocalDate deadline;
    private String jobType;
    private String jobUrl;
    private CompanyResponseDTO company;

    public JobApplicationResponseDTO() {
    }

    public JobApplicationResponseDTO(
            Long id,
            String jobTitle,
            String status,
            LocalDate applicationDate,
            LocalDate deadline,
            String jobType,
            String jobUrl,
            CompanyResponseDTO company) {

        this.id = id;
        this.jobTitle = jobTitle;
        this.status = status;
        this.applicationDate = applicationDate;
        this.deadline = deadline;
        this.jobType = jobType;
        this.jobUrl = jobUrl;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public CompanyResponseDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDTO company) {
        this.company = company;
    }

}
