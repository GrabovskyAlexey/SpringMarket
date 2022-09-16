package ru.grabovsky.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.orderservice.entity.DeliveryAddress;

public interface AddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
