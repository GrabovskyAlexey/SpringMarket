package ru.grabovsky.productfront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.grabovsky.productfront.dto.CartDto;
import ru.grabovsky.productfront.dto.CartItemDto;

@Service
@RequiredArgsConstructor
public class CartService {
    private final RestTemplate restTemplate;
    @Value("${url.cart}")
    private String url;

    public CartDto getCart(String cartId) {
        return restTemplate.getForEntity(url + "/" + cartId, CartDto.class).getBody();
    }

    public CartDto addProductToCart(CartItemDto item) {
        return restTemplate.postForEntity(url, item, CartDto.class).getBody();
    }

    public CartDto deleteProductFromCartById(String cartId, Long itemId) {
        return restTemplate.getForEntity(url + "/" + cartId + "/" + itemId, CartDto.class).getBody();
    }
}
