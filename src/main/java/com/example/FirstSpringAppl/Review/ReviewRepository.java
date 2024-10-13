package com.example.FirstSpringAppl.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(long companyId);

    @Query("select r from Review r join company c where c.id = :companyId and r.id = :reviewId")
    Review getReviewByCompanyIdAndReviewId(@Param("companyId") Long companyId, @Param("reviewId") Long reviewId);
}
