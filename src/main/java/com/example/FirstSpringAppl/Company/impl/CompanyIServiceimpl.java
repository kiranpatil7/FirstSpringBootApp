package com.example.FirstSpringAppl.Company.impl;

import com.example.FirstSpringAppl.Company.Company;
import com.example.FirstSpringAppl.Company.CompanyRepository;
import com.example.FirstSpringAppl.Company.CompanyService;
import com.example.FirstSpringAppl.job.Job;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyIServiceimpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company updateCompanyById(Long id, Company updatedCompany) {
      Company company= companyRepository.findById(id)
              .orElseThrow(()->new EntityNotFoundException(" Company not found with Id "+ id));
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            companyRepository.save(updatedCompany);
            return  company;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {

            if(companyRepository.existsById(id)){
                companyRepository.deleteById(id);
                return true;
            } else {
            return false;
        }
    }

}
