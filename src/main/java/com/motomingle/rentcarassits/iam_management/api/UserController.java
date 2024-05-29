package com.motomingle.rentcarassits.iam_management.api;

import com.motomingle.rentcarassits.iam_management.domain.service.UserService;
import com.motomingle.rentcarassits.iam_management.mapping.UserMapper;
import com.motomingle.rentcarassits.iam_management.resource.CreateUserResource;
import com.motomingle.rentcarassits.iam_management.resource.UpdateUserResource;
import com.motomingle.rentcarassits.iam_management.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Users", description = "Create, read, update and delete users")
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get All Users", description = "Get all users stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserResource.class))})
    })
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return userMapper.modelListPage(userService.getAll(), pageable);
    }

    @Operation(summary = "Get User by Id", description = "Get an user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class))})
    })
    @GetMapping("{userId}")
    public UserResource getInfoUserById(@PathVariable Long userId) {
        return userMapper.toResource(userService.getInfoUserById(userId));
    }

    @Operation(summary = "Create User", description = "Create User in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class)
                    ))
    })
    @PostMapping
    public UserResource createAgency(@RequestBody CreateUserResource resource){
        return userMapper.toResource(userService.create(userMapper.toModel(resource)));
    }

    @Operation(summary = "Update an User", description = "Update an User in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class)
                    ))
    })
    @PutMapping("/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return userMapper.toResource(userService.update(userId, userMapper.toModel(resource)));
    }

    @Operation(summary = "Delete an User", description = "Delete an Agency from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
