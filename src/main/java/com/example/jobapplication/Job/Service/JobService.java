package com.example.jobapplication.Job.Service;

import com.example.jobapplication.Dto.JobCreationDto;
import com.example.jobapplication.Job.Model.Job;

import java.util.List;


public interface JobService {

    List<Job> getalljobs();

    Job getJobById(Long id);

    boolean createjob(JobCreationDto job);

    boolean updatejob(Job job, Long id);

    boolean deletejobbyid(Long id);
}
