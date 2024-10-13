package com.example.FirstSpringAppl.Company;

import com.example.FirstSpringAppl.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @GetMapping("/companies") //Get all companies
    public ResponseEntity<List<Company>> findAll(){
        return  ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping("/companies")     //Create a Company
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return  new ResponseEntity<>("Company Created Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/companies/{id}")     //Create a jobs
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company updateCompany){
        Company updatedCompany = companyService.updateCompanyById(id,updateCompany);
        if(Objects.nonNull(updatedCompany))
            return  new ResponseEntity<>(updatedCompany,HttpStatus.FOUND);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getJobById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(Objects.nonNull(company))
            return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("/companies/{id}")     //Create a jobs
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean deleted = companyService.deleteCompanyById(id);
        if(deleted)
            return  new ResponseEntity<>("Company Deleted Successfully",HttpStatus.FOUND);
        return  new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
    }
}
