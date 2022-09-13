package ru.grabovsky.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.orderservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
