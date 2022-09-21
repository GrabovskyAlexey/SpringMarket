package ru.grabovsky.cartservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grabovsky.cartservice.dto.*;
import ru.grabovsky.cartservice.repositories.CartRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;

    private final UserService userService;
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    @Cacheable(value = "userCart", key = "#cartId")
    public CartDto getCart(String cartId) {
        return repository.getCartById(cartId);
    }

    @CachePut(value = "userCart", key = "#cartId")
    public CartDto addProductToCart(String cartId, CartItemDto item) {
        CartDto cart = getCart(cartId);
        if (cart.getCart() == null) {
            cart.setCart(new ArrayList<>());
        }

        int itemIndex = cart.getCart().indexOf(item);
        if (itemIndex == -1) {
            item.setId((long) (cart.getCart().size() + 1));
            cart.getCart().add(item);
        } else {
            CartItemDto cartItem = cart.getCart().get(itemIndex);
            cartItem.setCount(cartItem.getCount() + item.getCount());
        }
        return cart;
    }

    @CachePut(value = "userCart", key = "#cartId")
    public CartDto deleteProductFromCartById(String cartId, Long itemId) {
        CartDto cart = getCart(cartId);
        cart.setCart(
                cart.getCart().stream()
                        .filter(i -> !i.getId().equals(itemId))
                        .collect(Collectors.toList())
        );
        return cart;
    }

    @CacheEvict(value = "userCart", key = "#cartId")
    public void clear(String cartId) {
        getCart(cartId).getCart().clear();
    }

    @CachePut(value = "userCart", key = "#cartId")
    public CartDto createOrder(String cartId, DeliveryAddressDto addressDto, String username){
        CartDto cart = getCart(cartId);
        OrderDto order = OrderDto.builder()
                .address(addressDto)
                .status(OrderStatus.CREATED)
                .userId(userService.getUserIdByUsername(username))
                .items(cart.getCart().stream()
                        .map(item -> OrderItemDto.builder()
                                .count(item.getCount().intValue())
                                .price(BigDecimal.valueOf(item.getPrice()))
                                .productId(item.getProductId())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        kafkaTemplate.send("orders-to-create", order);
        cart.clear();
        return cart;
    }
}
