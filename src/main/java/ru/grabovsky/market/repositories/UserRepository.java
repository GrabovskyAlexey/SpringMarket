package ru.grabovsky.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.market.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsUserByUsernameOrEmail(String username, String email);
}
