package com.example.FirstSpringAppl.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
public class JobController {

    @Autowired
    JobService jobService;
    @GetMapping("/jobs")     //Get all jobs
    public ResponseEntity<List<Job>> findAll(){
        return  ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/jobs/hello")     //Get all jobs
    public ResponseEntity<String> getMsg(){
        return  new ResponseEntity<>("Hello World",HttpStatus.OK);
    }

    @PostMapping("/jobs")     //Create a jobs
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return  new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(Objects.nonNull(job))
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/jobs/{id}")     //Create a jobs
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return  new ResponseEntity<>("Job Deleted Successfully",HttpStatus.FOUND);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")     //Create a jobs
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id,updatedJob);
        if(updated)
            return  new ResponseEntity<>("Job Updated Successfully",HttpStatus.FOUND);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
