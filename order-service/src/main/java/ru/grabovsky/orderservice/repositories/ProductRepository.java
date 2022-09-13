package ru.grabovsky.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.orderservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
