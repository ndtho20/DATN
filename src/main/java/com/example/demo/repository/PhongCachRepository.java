package com.example.demo.repository;


import com.example.demo.entity.PhongCach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongCachRepository extends JpaRepository<PhongCach, Integer> {
}
