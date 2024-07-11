package com.example.jobapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCreationDto {
    private String name;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;
}
