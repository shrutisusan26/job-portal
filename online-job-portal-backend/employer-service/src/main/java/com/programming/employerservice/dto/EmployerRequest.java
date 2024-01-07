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
public class EmployerRequest {
    private Long userId;
    private String companyName;
    private String foundedIn;
    private String companyCEO;
    private String companyDescription;
}
