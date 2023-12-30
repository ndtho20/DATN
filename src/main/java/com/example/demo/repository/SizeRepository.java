package com.example.demo.repository;


import com.example.demo.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

    @Query("SELECT p FROM Size p WHERE p.ma = ?1")
    List<Size> findByMa(String ma);
}
