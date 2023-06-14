package com.example.fullstackdevelopement.repository;

import com.example.fullstackdevelopement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
