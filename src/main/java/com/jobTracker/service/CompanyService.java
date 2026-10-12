package com.jobTracker.service;
import java.util.List;
import com.jobTracker.dto.CompanyResponseDTO;
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


    public CompanyResponseDTO createCompany(Company company) {
        Company savedCompany = companyRepository.save(company);

        return convertToDTO(savedCompany);
    }




    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company with id " + id + " not found"
                        ));

        return convertToDTO(company);
    }


    public CompanyResponseDTO updateCompany(Long id, Company updatedCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company with id " + id + " not found"
                        ));

        company.setName(updatedCompany.getName());
        company.setWebsite(updatedCompany.getWebsite());
        company.setLocation(updatedCompany.getLocation());
        company.setDescription(updatedCompany.getDescription());

        Company savedCompany = companyRepository.save(company);
        return convertToDTO(savedCompany);
    }

    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Company with id " + id + " not found"
            );
        }
        companyRepository.deleteById(id);
    }

    private CompanyResponseDTO convertToDTO(Company company) {
        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getWebsite(),
                company.getLocation(),
                company.getDescription()
        );
    }

}
