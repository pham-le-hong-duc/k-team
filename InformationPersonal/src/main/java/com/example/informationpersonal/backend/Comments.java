package com.example.informationpersonal.backend;

import jakarta.persistence.*;
import org.atmosphere.config.service.Post;

import java.security.Timestamp;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String content;

    private Timestamp created_at;

    // Getters and setters
}
