package com.example.jobapplication.Reviews.Service;

import com.example.jobapplication.Reviews.Model.Review;

import java.util.List;

public interface ReviewService {
   

    List<Review> getallreview(Long companyId);

    Review getallreviewbyreviewid(Long companyId, Long reviewId);

    boolean addreview(Review review, Long companyId);

    boolean updatereview(Long companyId, Long reviewId, Review review);

    boolean deletereview(Long companyId, Long reviewId);
}
