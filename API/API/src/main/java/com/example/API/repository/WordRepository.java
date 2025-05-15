package com.example.API.repository;

import com.example.API.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByUserId(Long userId);
    Optional<Word> findByIdAndUserId(Integer id, Long userId);
}