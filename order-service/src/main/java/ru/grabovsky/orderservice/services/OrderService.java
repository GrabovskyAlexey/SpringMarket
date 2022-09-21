package ru.grabovsky.orderservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.grabovsky.orderservice.dto.OrderDto;
import ru.grabovsky.orderservice.entity.*;
import ru.grabovsky.orderservice.repositories.OrderRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    @KafkaListener(topics = "orders-to-create", groupId = "orders")
    public Order createOrder(OrderDto dto) {
        Order order = new Order();
        if (dto.getAddress().getId() != null) {
            order.setAddress(addressService.findById(dto.getAddress().getId()));
        } else {
            order.setAddress(DeliveryAddress.builder()
                    .country(dto.getAddress().getCountry())
                    .city(dto.getAddress().getCity())
                    .region(dto.getAddress().getRegion())
                    .street(dto.getAddress().getStreet())
                    .house(dto.getAddress().getHouse())
                    .flat(dto.getAddress().getFlat())
                    .user(userService.getUserById(dto.getUserId()))
                    .build());
        }
        order.setStatus(dto.getStatus());
        order.setUser(userService.getUserById(dto.getUserId()));
        order.setItems(
                dto.getItems().stream()
                        .map(item -> OrderItem.builder()
                                .order(order)
                                .price(item.getPrice())
                                .count(item.getCount())
                                .product(productService.getProductById(item.getProductId()))
                                .build())
                        .collect(Collectors.toSet()));
        order.getItems().forEach(item -> log.debug("product: {}, item: {}", item.getProduct(), item));
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public void changeOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUsername(String username){
        User user = userService.getUserByUsername(username);
        return orderRepository.findAllByUser(user);
    }

}
