package com.motomingle.rentcarassits.iam_management.domain.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User create(User user);
    User update(Long userId, User user);
    User getInfoUserById(Long userId);
    ResponseEntity<?> delete(Long userId);
}
