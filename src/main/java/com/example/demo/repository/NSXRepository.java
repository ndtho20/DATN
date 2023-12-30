package com.example.demo.repository;

import com.example.demo.entity.NSX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NSXRepository extends JpaRepository<NSX,Integer> {

    @Query("SELECT p FROM NSX p WHERE p.ma = ?1")
    List<NSX> findByMa(String ma);

}
