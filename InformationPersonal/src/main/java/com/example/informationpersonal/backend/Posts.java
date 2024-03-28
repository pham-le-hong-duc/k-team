package com.example.informationpersonal.backend;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String title;

    private String content;

    private Timestamp created_at;

    private Timestamp updated_at;

    // Getters and setters
}
