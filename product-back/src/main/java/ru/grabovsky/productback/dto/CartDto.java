package ru.grabovsky.productback.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash
public class CartDto {
    private String id;
    private List<CartItemDto> cart;
}
