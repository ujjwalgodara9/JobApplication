package com.example.jobapplication.Job.Service;

import com.example.jobapplication.Company.Model.Company;
import com.example.jobapplication.Company.Respository.CompanyRepository;
import com.example.jobapplication.Dto.JobCreationDto;
import com.example.jobapplication.Job.Model.Job;
import com.example.jobapplication.Job.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;



    @Override
    public List<Job> getalljobs() {
        try{
            return jobRepository.findAll();
        }catch (Exception e){
            System.out.println("Error in getalljobs");
            return new ArrayList<>();
        }

    }

    @Override
    public Job getJobById(Long id) {
        try{
            Optional<Job> job = jobRepository.findById(id);
            return job.orElse(null);
        } catch (Exception e){
            return null;
        }
//        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createjob(JobCreationDto jobCreationDto) {
//        try{
//            jobRepository.save(job);
//            return true;
//        }
//        catch(Exception e){
//            System.out.println("Error in Creating Job");
//            return false;
//        }

        try{
            Company company = companyRepository.findById(jobCreationDto.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));

            Job job = Job.builder()
                    .name(jobCreationDto.getName())
                    .description(jobCreationDto.getDescription())
                    .minSalary(jobCreationDto.getMinSalary())
                    .maxSalary(jobCreationDto.getMaxSalary())
                    .location(jobCreationDto.getLocation())
                    .company(company)
                    .build();

            jobRepository.save(job);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatejob(Job job, Long id) {

        try {
            Optional<Job> joboptional = jobRepository.findById(id);

            if (joboptional.isPresent()) {
                Job getjob = joboptional.get();

                Job updatedJob = Job.builder()
                        .id(getjob.getId())
                        .name(job.getName()!= null ? job.getName() : getjob.getName())
                        .description(job.getDescription()!= null ? job.getDescription() : getjob.getDescription())
                        .location(job.getLocation()!= null ? job.getLocation() : getjob.getLocation())
                        .maxSalary(job.getMaxSalary()!= null ? job.getMaxSalary() : getjob.getMaxSalary())
                        .minSalary(job.getMinSalary()!= null ? job.getMinSalary() : getjob.getMinSalary())
                        .company(job.getCompany())
                        .build();
                jobRepository.save(getjob);
                System.out.println("Job updated");
                return true;


                //                getjob.setName(job.getName());
//                getjob.setDescription(job.getDescription());
//                getjob.setLocation(job.getLocation());
//                getjob.setMaxSalary(job.getMaxSalary());
//                getjob.setMinSalary(job.getMinSalary());
            }

            System.out.println("No Job Found");
            return false;

        }catch (Exception e){
            System.out.println("Error in Updating Job");
            return false;
        }

    }

    @Override
    public boolean deletejobbyid(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Error in Deleting Job");
            return false;
        }

    }
}
