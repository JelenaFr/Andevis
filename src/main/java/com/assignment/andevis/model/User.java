package com.assignment.andevis.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    private LocalDateTime registrationTime;


    public User(String password, String email, String firstName, String lastName) {
        this.username = firstName.substring(0, 1).toUpperCase() + firstName.substring(1)
            + " " + lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationTime = LocalDateTime.now();
    }
}
