package ru.grabovsky.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.authservice.models.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
