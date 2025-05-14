package com.ltf.financecontrol.modules.user.model.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    @NotNull
    private String username;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "createdAt")
    @NotNull
    private LocalDateTime createdAt;

}
