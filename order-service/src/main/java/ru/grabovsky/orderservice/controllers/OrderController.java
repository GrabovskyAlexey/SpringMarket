package ru.grabovsky.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.orderservice.dto.OrderDto;
import ru.grabovsky.orderservice.mappers.OrderMapper;
import ru.grabovsky.orderservice.services.OrderService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId) {
        return mapper.mapToDto(orderService.getOrderById(orderId));
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        List<OrderDto> orders = orderService.getOrdersByUsername(username).stream()
                .map(mapper::mapToDto).collect(Collectors.toList());
        orders.forEach(OrderDto::recalculate);
        return orders;
    }

    @PostMapping
    public Long createOrder(@RequestBody OrderDto order) {
        return orderService.createOrder(order).getId();
    }

}
