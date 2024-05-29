package com.motomingle.rentcarassits.iam_management.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String names;
    private String lastnames;
    private String dni;
    private String profilePicture;
}
