package ru.grabovsky.cartservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    private OrderStatus status;
    private Long userId;
    private DeliveryAddressDto address;
    private List<OrderItemDto> items;
}
