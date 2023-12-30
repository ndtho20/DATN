package com.example.demo.repository;


import com.example.demo.entity.PhongCach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongCachRepository extends JpaRepository<PhongCach, Integer> {

    @Query("SELECT p FROM PhongCach p WHERE p.ma = ?1")
    List<PhongCach> findByMa(String ma);
}
