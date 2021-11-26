package com.example.bpotp.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_auth",
        uniqueConstraints = @UniqueConstraint(columnNames = "phoneNumber"))
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 15)
    private String phoneNumber;
}
