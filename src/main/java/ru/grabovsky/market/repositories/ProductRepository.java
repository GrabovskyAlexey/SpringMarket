package ru.grabovsky.market.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.market.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductByPriceBetween(int min, int max, Pageable pageable);
    Page<Product> findProductByPriceAfter(int min, Pageable pageable);
    Page<Product> findProductByPriceBefore(int max, Pageable pageable);
}
