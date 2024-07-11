package com.example.jobapplication.Reviews.Respository;

import com.example.jobapplication.Company.Model.Company;
import com.example.jobapplication.Reviews.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findByCompanyId(Long companyId);
}
