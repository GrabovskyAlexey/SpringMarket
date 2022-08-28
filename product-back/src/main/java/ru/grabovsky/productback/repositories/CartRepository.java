package ru.grabovsky.productback.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.grabovsky.productback.dto.CartDto;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public CartDto getCartById(String id){
        String cacheId = "userCart::"+id;
        if(!redisTemplate.hasKey(cacheId)){
            CartDto cartDto = new CartDto();
            cartDto.setId(id);
            cartDto.setCart(new ArrayList<>());
            redisTemplate.opsForValue().set(cacheId, cartDto);
        }
        return (CartDto) redisTemplate.opsForValue().get(cacheId);
    }
}
