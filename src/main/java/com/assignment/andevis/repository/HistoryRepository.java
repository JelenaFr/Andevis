package com.assignment.andevis.repository;

import com.assignment.andevis.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findHistoryByUserId(Long id);

    void deleteById(Long id);
}
