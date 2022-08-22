package ru.grabovsky.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.dto.CartDto;
import ru.grabovsky.market.dto.CartItemDto;
import ru.grabovsky.market.models.OrderItem;
import ru.grabovsky.market.repositories.CartRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;

    @Cacheable(value = "userCart", key = "#cartId")
    public CartDto getCart(String cartId) {
        System.out.println("no cache");
        return repository.getCartById(cartId);
    }

    @CachePut(value = "userCart", key = "#cartId")
    public CartDto addProductToCart(String cartId, CartItemDto item) {
        CartDto cart = getCart(cartId);
        if (cart.getCart() == null) {
            cart.setCart(new ArrayList<>());
        }
//        cart.getCart().stream().filter(i -> item.getProduct().)

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
    }
}
