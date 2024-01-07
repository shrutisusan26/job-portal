package com.programming.employerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResponse {
    public long empId;
    public String companyName;
    public Date foundedIn;
    public String CEO;
    public String companyDescription;
    public Long userId;

}
