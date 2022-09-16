package ru.grabovsky.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.orderservice.dto.OrderDto;
import ru.grabovsky.orderservice.mappers.OrderMapper;
import ru.grabovsky.orderservice.services.OrderService;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId) {

        return mapper.mapToDto(orderService.getOrderById(orderId));
    }

    @PostMapping
    public Long createOrder(@RequestBody OrderDto order) {
        return orderService.createOrder(order).getId();
    }

}
