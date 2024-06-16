package com.motomingle.rentcarassits.iam_management.domain.model.entity;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "username")
    private String username;

    @NotBlank
    @NotNull
    @Column(name = "password")
    private String password;

    @NotBlank
    @NotNull
    @Column(name = "email")
    private String email;

    @NotBlank
    @NotNull
    @Column(name = "names")
    private String names;

    @NotBlank
    @NotNull
    @Column(name = "lastnames")
    private String lastnames;

    @NotBlank
    @NotNull
    @Column(name = "dni")
    private String dni;

    @NotBlank
    @NotNull
    @Column(name = "profile_picture")
    private String profilePicture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;
}
