package com.cvr.sbook.repository;
import com.cvr.sbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This allows us to find a user by their email for login
    Optional<User> findByEmail(String email);
}