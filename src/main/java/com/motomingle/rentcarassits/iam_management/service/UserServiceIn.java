package com.motomingle.rentcarassits.iam_management.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.User;
import com.motomingle.rentcarassits.iam_management.domain.persistence.UserRepository;
import com.motomingle.rentcarassits.iam_management.domain.service.UserService;
import com.motomingle.rentcarassits.shared.exception.ResourceNotFoundException;
import com.motomingle.rentcarassits.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceIn implements UserService {
    private static final String ENTITY = "User";

    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceIn(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        User userWithUsername = userRepository.findByUsername(user.getUsername());
        if (userWithUsername != null)
            throw new ResourceValidationException(ENTITY, "An user with the same username already exits.");


        User userWithEmail = userRepository.findByEmail(user.getEmail());
        if (userWithEmail != null)
            throw new ResourceValidationException(ENTITY, "An user with the same email already exists.");

        User userWithDNI = userRepository.findByDni(user.getDni());
        if (userWithDNI != null)
            throw new ResourceValidationException(ENTITY, "An user with the same dni already exists.");

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        return userRepository.findById(userId).map(user ->
                userRepository.save(user.withUsername(request.getUsername())
                        .withPassword(request.getPassword())
                        .withEmail(request.getEmail())
                        .withNames(request.getNames())
                        .withLastnames(request.getLastnames())
                        .withDni(request.getDni())
                        .withProfilePicture(request.getProfilePicture())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User getInfoUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(
                user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
