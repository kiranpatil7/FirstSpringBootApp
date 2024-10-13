package com.example.FirstSpringAppl.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByCompanyId(long companyId);

    boolean createReview(long companyId, Review review);

    Review getReviewBYCompanyIdAndReviewId(Long companyId, Long reviewId);

    Review updateReviewByCompanyIdAndReviewId(Long companyId, Long reviewId, Review review);

    boolean deleteReviewByCompanyByIdAndReviewById(Long companyId, Long reviewId);
}
