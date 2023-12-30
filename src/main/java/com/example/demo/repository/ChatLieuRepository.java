package com.example.demo.repository;

import com.example.demo.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Integer> {

    @Query("SELECT p FROM ChatLieu p WHERE p.ma = ?1")
    List<ChatLieu> findByMa(String ma);
}
