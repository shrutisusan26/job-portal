package com.programming.jobservice.repository;
import org.springframework.data.jpa.repository.Query;


import com.programming.jobservice.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    @Query("SELECT j FROM Job j WHERE j.userId = :userId")
    List<Job> findByUserId(long userId);
}
