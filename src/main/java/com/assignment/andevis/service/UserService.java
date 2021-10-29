package com.assignment.andevis.service;


import com.assignment.andevis.model.User;
import com.assignment.andevis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstName(user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1).toLowerCase());
        user.setLastName(user.getLastName().substring(0, 1).toUpperCase()+ user.getLastName().substring(1).toLowerCase());
        user.setUsername(user.getFirstName()+" "+ user.getLastName());
        user.setRegistrationTime(LocalDateTime.now());
        userRepository.save(user);

    }

}
