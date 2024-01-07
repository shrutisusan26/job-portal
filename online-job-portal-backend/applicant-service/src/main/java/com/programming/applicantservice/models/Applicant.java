package com.programming.applicantservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "applicant")
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ApplicantId;
    private String applicantName;
    private String resumeUrl;
    private String coverLetter;
    private String address;
    private String skills;
}
