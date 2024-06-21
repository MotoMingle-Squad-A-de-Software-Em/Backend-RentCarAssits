package com.motomingle.rentcarassits.iam_management.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String names;

    @NotBlank
    @NotNull
    private String lastnames;

    @NotBlank
    @NotNull
    private String dni;

    @NotBlank
    @NotNull
    private String profilePicture;
}
