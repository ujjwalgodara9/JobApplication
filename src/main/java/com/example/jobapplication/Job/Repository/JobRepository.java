package com.example.jobapplication.Job.Repository;

import com.example.jobapplication.Job.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
