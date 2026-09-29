package com.ticket.bookingApplication.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
    @Column(length = 90)
    private String name;
    private String gender;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String role;
}
