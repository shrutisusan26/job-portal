package com.programming.applicationservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;
    private Long empId;
    private long jobId;
    private Long userId;

}
