package com.assignment.andevis.repository;

import com.assignment.andevis.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findHistoryByUserId(Long id);

    void deleteById(Long id);

}
