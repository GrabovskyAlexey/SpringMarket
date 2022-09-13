package ru.grabovsky.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grabovsky.orderservice.entity.OrderStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private OrderStatus status;
    private Long userId;
    private DeliveryAddressDto address;
    private List<OrderItemDto> items;
}
