package com.example.informationpersonal.backend;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String username;

    private String email;

    private String password;

    private String full_name;

    private Date date_of_birth;

    private String gender;

    private String phone_number;

    private String address;

}
