package com.example.FirstSpringAppl.Company;

import com.example.FirstSpringAppl.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company updateCompanyById(Long id, Company updatedCompany);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);
}
