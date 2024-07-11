package com.example.jobapplication.Company.Respository;

import com.example.jobapplication.Company.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
