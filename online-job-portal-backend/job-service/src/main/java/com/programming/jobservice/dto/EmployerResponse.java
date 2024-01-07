package com.programming.jobservice.dto;

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
    public long id;
    public String companyName;
    public Date foundedIn;
    public String CEO;
    public String companyDescription;
    public Long userId;

}
