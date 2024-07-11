package com.example.jobapplication.Company.Service;

import com.example.jobapplication.Company.Model.Company;
import com.example.jobapplication.Company.Respository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getallcompany() {
        try{
            return companyRepository.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    @Override
    public Company getcompanybyId(Long id) {
        try{
            Optional<Company> company = companyRepository.findById(id);
            return company.orElse(null);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean addcomapny(Company company) {
        try{
            companyRepository.save(company);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatecompany(Long id, Company company) {
        try{
            Optional<Company> company1 = companyRepository.findById(id);

            if(company1.isPresent()) {

                Company getcomapny = company1.get();

                Company updatedcompany = Company.builder()
                        .id(getcomapny.getId())
                        .name(company.getName()!= null ? company.getName() : getcomapny.getName() )
                        .description(company.getDescription()!= null ? company.getDescription() : getcomapny.getDescription())
                        .jobs(company.getJobs())
                        .build();

                companyRepository.save(updatedcompany);
                return true;
            }

            return false;

            }catch (Exception e){
                return false;
            }
        }

    @Override
    public boolean deletecompanybyid(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

