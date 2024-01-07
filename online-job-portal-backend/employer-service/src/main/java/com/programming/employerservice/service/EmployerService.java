package com.programming.employerservice.service;

import com.programming.employerservice.dto.EmployerRequest;
import com.programming.employerservice.dto.EmployerResponse;
import com.programming.employerservice.models.Employer;
import com.programming.employerservice.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private  final EmployerRepository employerRepository;
    public List<EmployerResponse> getEmployers() {
        return employerRepository.findAll().stream().map(employer-> {
            return EmployerResponse.builder()
                    .empId(employer.getEmpId())
                    .companyName(employer.getCompanyName())
                    .foundedIn(employer.getFoundedIn())
                    .companyDescription(employer.getCompanyDescription())
                    .CEO(employer.getCEO())
                    .userId(employer.getUserId())
                    .build();

        }).toList();
    }

    public EmployerResponse getEmployerById(long id) throws  Exception{
        Employer employer =  employerRepository.findByUserId(id).orElseThrow();
        return EmployerResponse.builder()
                .empId(employer.getEmpId())
                .companyDescription(employer.getCompanyDescription())
                .companyName(employer.getCompanyName())
                .foundedIn(employer.getFoundedIn())
                .CEO(employer.getCEO())
                .userId(employer.getUserId())
                .build();

    }

    public EmployerResponse registerEmployer(EmployerRequest employerRequest) throws ParseException {
        Employer employer = new Employer();
        employer.setCEO(employerRequest.getCompanyCEO());
        employer.setCompanyName(employerRequest.getCompanyName());
        employer.setFoundedIn(new SimpleDateFormat("yyyy").parse(employerRequest.getFoundedIn()));
        employer.setCompanyDescription(employerRequest.getCompanyDescription());
        employer.setUserId(employerRequest.getUserId());
        employerRepository.save(employer);
        return EmployerResponse.builder()
                .empId(employer.getEmpId())
                .companyDescription(employer.getCompanyDescription())
                .companyName(employer.getCompanyName())
                .foundedIn(employer.getFoundedIn())
                .CEO(employer.getCEO())
                .userId(employer.getUserId())
                .build();
    }


}
