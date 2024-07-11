package com.example.jobapplication.Reviews.Controller;

import com.example.jobapplication.Reviews.Model.Review;
import com.example.jobapplication.Reviews.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId) {
        List<Review> review = reviewService.getallreview(companyId);

        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId) {
        boolean added = reviewService.addreview(review,companyId);

        if(!added){
            return new ResponseEntity<>("Not able to add review",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Added review",HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getallreviewbyreviewid(companyId,reviewId);

        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean updated = reviewService.updatereview(companyId,reviewId, review);

        if(!updated){
            return new ResponseEntity<>("Error while Updating Review",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated Review",HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean deleted = reviewService.deletereview(companyId,reviewId);

        if(!deleted){
            return new ResponseEntity<>("Error while Deleting",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted Review",HttpStatus.OK);
    }

}
