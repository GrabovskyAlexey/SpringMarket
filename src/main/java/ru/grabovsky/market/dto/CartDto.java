package ru.grabovsky.market.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import ru.grabovsky.market.models.OrderItem;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@RedisHash
public class CartDto {
    private String id;
    private List<CartItemDto> cart;
}
