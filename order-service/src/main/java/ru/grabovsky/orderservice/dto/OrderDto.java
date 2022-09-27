package ru.grabovsky.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grabovsky.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
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
    private BigDecimal totalPrice;

    public void recalculate(){
        totalPrice = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
