package com.example.jobapplication.Job.Controller;

import com.example.jobapplication.Dto.JobCreationDto;
import com.example.jobapplication.Job.Model.Job;
import com.example.jobapplication.Job.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;



    @GetMapping
    public ResponseEntity<List<Job>> findAll(){

        List<Job> getalljobs=jobService.getalljobs();

        if(getalljobs.size()==0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getalljobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id){
        Job getjobbyid=jobService.getJobById(id);

        if(getjobbyid==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getjobbyid,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobCreationDto> CreateJob(@RequestBody JobCreationDto job){
        boolean created=jobService.createjob(job);

        if(!created){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> UpdateJob(@RequestBody Job job, @PathVariable Long id){
        boolean updated=jobService.updatejob(job,id);

        if(!updated){
            return new ResponseEntity<>("Not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id){
        boolean deleted=jobService.deletejobbyid(id);

        if(!deleted){
            return new ResponseEntity<>("Not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
    }
}
