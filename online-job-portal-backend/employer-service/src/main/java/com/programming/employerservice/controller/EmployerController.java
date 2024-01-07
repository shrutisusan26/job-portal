package com.programming.employerservice.controller;


import com.programming.employerservice.dto.EmployerRequest;
import com.programming.employerservice.dto.EmployerResponse;
import com.programming.employerservice.repository.EmployerRepository;
import com.programming.employerservice.service.EmployerService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployerResponse> getAllEmployers(){
        return employerService.getEmployers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployerResponse getEmployerById(@PathVariable String id ) throws Exception {
        long parseID = Long.parseLong(id);
        return  employerService.getEmployerById(parseID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployerResponse createEmployer(@RequestBody EmployerRequest employerRequest) throws ParseException {
        System.out.println("In controller");
       return  employerService.registerEmployer(employerRequest);
    }
}
