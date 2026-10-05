package com.jobTracker.service;

import com.jobTracker.entity.Company;
import com.jobTracker.exception.ResourceNotFoundException;
import com.jobTracker.repository.CompanyRepository;

import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Company getCompanyById(Long id) {

        return companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company with id " + id + " not found"
                        )
                );
    }

    public Company updateCompany(Long id, Company updatedCompany) {

        Company existingCompany = companyRepository.findById(id).orElse(null);

        if (existingCompany == null) {
            return null;
        }

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setWebsite(updatedCompany.getWebsite());
        existingCompany.setLocation(updatedCompany.getLocation());
        existingCompany.setDescription(updatedCompany.getDescription());

        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
