package com.example.informationpersonal.backend;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "follows")
public class Follows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int follow_id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Users followed;

    private Timestamp created_at;

    // Getters and setters
}
