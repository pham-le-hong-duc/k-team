package com.example.informationpersonal.backend;

import jakarta.persistence.*;
import org.atmosphere.config.service.Post;

import java.security.Timestamp;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int like_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private Timestamp created_at;

    // Getters and setters
}
