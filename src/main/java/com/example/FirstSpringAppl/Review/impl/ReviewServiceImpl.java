package com.example.FirstSpringAppl.Review.impl;

import com.example.FirstSpringAppl.Company.Company;
import com.example.FirstSpringAppl.Company.CompanyService;
import com.example.FirstSpringAppl.Review.Review;
import com.example.FirstSpringAppl.Review.ReviewRepository;
import com.example.FirstSpringAppl.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CompanyService companyService;
    @Override
    public List<Review> findByCompanyId(long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(Objects.nonNull(company)){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewBYCompanyIdAndReviewId(Long companyId, Long reviewId) {
        return reviewRepository.getReviewByCompanyIdAndReviewId(companyId,reviewId);
    }

    @Override
    public Review updateReviewByCompanyIdAndReviewId(Long companyId, Long reviewId, Review review) {
       List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
       Review firstreview = reviewList.stream().filter(r -> r.getId()== reviewId).findFirst().orElse(null);
       if(Objects.nonNull(firstreview)){
           firstreview.setDescription(review.getDescription());
           firstreview.setRating(review.getRating());
           firstreview.setCompany(review.getCompany());
           reviewRepository.save(firstreview);
           return firstreview;
       }
        return null;
    }

    @Override
    public boolean deleteReviewByCompanyByIdAndReviewById(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompanyById(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return  false;
    }
}
