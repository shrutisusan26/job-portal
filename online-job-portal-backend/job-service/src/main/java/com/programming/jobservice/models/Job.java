package com.programming.jobservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;

    @Column(name = "userId")  // Specify the column name for empId
    private Long userId;
}
