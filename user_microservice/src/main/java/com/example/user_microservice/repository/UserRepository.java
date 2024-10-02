package com.example.user_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_microservice.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long>{
    
}
