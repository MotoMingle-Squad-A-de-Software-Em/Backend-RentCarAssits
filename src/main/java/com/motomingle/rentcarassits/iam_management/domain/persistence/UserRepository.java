package com.motomingle.rentcarassits.iam_management.domain.persistence;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByDni (String dni);
    User findByUsernameAndPassword(String username, String password);
}
