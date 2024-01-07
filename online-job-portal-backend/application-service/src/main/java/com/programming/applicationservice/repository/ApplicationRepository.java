package com.programming.applicationservice.repository;

import com.programming.applicationservice.dto.ApplicationResponse;
import com.programming.applicationservice.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a from Application a where a.empId = :empId")
    List<Application> findByEmpId(long empId);

    @Query("SELECT a from Application a where a.jobId = :jobId and a.userId = :userId")
    Application findByEmpIdAndJobId(long jobId, long userId);

    @Query("SELECT a from Application a where a.userId = :userId")
    List<Application> findByUserId(long userId);
}
