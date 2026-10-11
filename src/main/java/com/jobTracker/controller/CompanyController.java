package com.jobTracker.controller;
import com.jobTracker.dto.CompanyResponseDTO;

import com.jobTracker.entity.Company;
import com.jobTracker.service.CompanyService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDTO createCompany(@Valid @RequestBody Company company) {
        return companyService.createCompany(company);
    }


    @GetMapping
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }


    @GetMapping("/{id}")
    public CompanyResponseDTO getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }


    @PutMapping("/{id}")
    public  CompanyResponseDTO updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody Company company) {

        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
