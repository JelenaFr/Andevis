package com.assignment.andevis.service;

import com.assignment.andevis.model.History;
import com.assignment.andevis.model.User;
import com.assignment.andevis.repository.HistoryRepository;
import com.assignment.andevis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    UserRepository userRepository;

    public List<History> findUserHistory(Principal principal) {
        String currentPrincipalName = principal.getName();
        User user = userRepository.findUserByEmail(currentPrincipalName);
        return historyRepository.findHistoryByUserId(user.getId());
    }

    public void deleteEntry(Long id) {
        historyRepository.deleteById(id);
    }
}
