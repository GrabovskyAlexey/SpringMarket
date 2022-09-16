package ru.grabovsky.cartservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDto {
    private Long id;
    private int count;
    private BigDecimal price;
    private Long productId;
}
