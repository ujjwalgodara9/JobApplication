package com.example.jobapplication.Reviews.Service;

import com.example.jobapplication.Company.Model.Company;
import com.example.jobapplication.Company.Service.CompanyService;
import com.example.jobapplication.Reviews.Model.Review;
import com.example.jobapplication.Reviews.Respository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CompanyService companyService;


    @Override
    public List<Review> getallreview(Long companyId) {
        Optional<List<Review>> review = reviewRepository.findByCompanyId(companyId);

        if (review.isPresent()) {
            return review.orElse(null);
        }

        return null;

    }

    @Override
    public Review getallreviewbyreviewid(Long companyId, Long reviewId) {

        List<Review> reviews = getallreview(companyId);
        if (reviews != null) {
            return reviews.stream()
                    .filter(review -> review.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);

        }

        return null;
    }

    @Override
    public boolean addreview(Review review, Long companyId) {
        Company company=companyService.getcompanybyId(companyId);
        if(company!=null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }

    @Override
    public boolean updatereview(Long companyId, Long reviewId, Review review) {
        //Company company=companyService.getcompanybyId(companyId);
//        Review new_review=getallreviewbyreviewid(companyId,reviewId);
//        if(new_review!=null){
//            Review getreview = Review.builder()
//                    .id(new_review.getId())
//                    .description((review.getDescription()))
//                    .title(review.getTitle())
//                    .rating(review.getRating())
//                    .company(review.getCompany())
//                    .build();
//            reviewRepository.save(getreview);
//            return true;
//        }
//        return false;

        Review existingReview = getallreviewbyreviewid(companyId, reviewId);
        if (existingReview != null) {
            Review updatedReview = Review.builder()
                    .id(existingReview.getId())
                    .description(review.getDescription() != null ? review.getDescription() : existingReview.getDescription())
                    .title(review.getTitle() != null ? review.getTitle() : existingReview.getTitle())
                    .rating(review.getRating() != 0 ? review.getRating() : existingReview.getRating())
                    .company(existingReview.getCompany()) // Keep the existing company association
                    .build();
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletereview(Long companyId, Long reviewId) {
        Company company=companyService.getcompanybyId(companyId);
        Review review=getallreviewbyreviewid(companyId,reviewId);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
