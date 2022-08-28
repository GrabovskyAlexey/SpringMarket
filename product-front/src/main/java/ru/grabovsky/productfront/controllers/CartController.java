package ru.grabovsky.productfront.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.productfront.dto.CartDto;
import ru.grabovsky.productfront.dto.CartItemDto;
import ru.grabovsky.productfront.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(value = {"", "/{cartId}"})
    public CartDto getCart(@PathVariable(required = false) String cartId) {
        if (cartId == null) {
            cartId = UUID.randomUUID().toString();
        }
        return cartService.getCart(cartId);
    }

    @PutMapping("")
    public CartDto addProductToCart(@RequestBody CartItemDto item) {
        return cartService.addProductToCart(item);
    }

    @DeleteMapping("/{cartId}/{itemId}")
    public CartDto deleteProductFromCart(@PathVariable String cartId, @PathVariable Long itemId) {
        return cartService.deleteProductFromCartById(cartId, itemId);
    }

}
