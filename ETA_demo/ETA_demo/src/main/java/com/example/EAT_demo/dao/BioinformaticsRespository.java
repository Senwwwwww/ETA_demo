package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Bioinformatics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BioinformaticsRespository extends JpaRepository<Bioinformatics, Long> {
    @Query("SELECT b FROM Bioinformatics b WHERE b.user.userId = :userId")
    Bioinformatics findByUserId(Long userId);
}
