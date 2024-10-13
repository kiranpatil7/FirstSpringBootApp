package com.example.FirstSpringAppl.Review;


import com.example.FirstSpringAppl.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews") //Get all Reviews
    public ResponseEntity<List<Review>> findAll(@PathVariable Long companyId){
        return  ResponseEntity.ok(reviewService.findByCompanyId(companyId));
    }
    @PostMapping("/reviews")     //Create a CompanyReview
    public ResponseEntity<String> createCompanyReview(@PathVariable long companyId,@RequestBody Review review){
       boolean added= reviewService.createReview(companyId,review);
       if(added){
           return  new ResponseEntity<>("Company Review Created", HttpStatus.CREATED);
       } else {
           return  new ResponseEntity<>("Company Review Not Added", HttpStatus.NOT_FOUND);
       }

    }
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewByCompanyIdAndReviewId(@PathVariable Long companyId, @PathVariable Long reviewId ){
        Review review = reviewService.getReviewBYCompanyIdAndReviewId(companyId,reviewId);
        if(Objects.nonNull(review))
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/review/{reviewId}")     //Create a jobs
    public ResponseEntity<Review> updateReviewByCompanyIdAndReviewId(@PathVariable Long companyId,@PathVariable Long reviewId , @RequestBody Review review){
        Review updatedReview = reviewService.updateReviewByCompanyIdAndReviewId(companyId,reviewId,review);
        if(Objects.nonNull(updatedReview))
            return  new ResponseEntity<>(updatedReview,HttpStatus.FOUND);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/review/{reviewId}")     //Create a jobs
    public ResponseEntity<String> deleteReviewByCompanyByIdAndReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReviewByCompanyByIdAndReviewById(companyId,reviewId);
        if(deleted)
            return  new ResponseEntity<>("Review Deleted Successfully",HttpStatus.FOUND);
        return  new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);
    }
}
