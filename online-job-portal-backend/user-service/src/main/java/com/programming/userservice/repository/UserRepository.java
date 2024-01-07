package com.programming.userservice.repository;

import com.programming.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("Select User from User u where u.email= :email")
    Optional<User> findByEmail(String email);
}
