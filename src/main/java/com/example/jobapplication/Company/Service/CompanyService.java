package com.example.jobapplication.Company.Service;

import com.example.jobapplication.Company.Model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getallcompany();

    Company getcompanybyId(Long id);

    boolean addcomapny(Company company);

    boolean updatecompany(Long id, Company company);

    boolean deletecompanybyid(Long id);
}
