package ru.grabovsky.cartservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grabovsky.cartservice.dto.CartDto;
import ru.grabovsky.cartservice.dto.CartItemDto;
import ru.grabovsky.cartservice.dto.DeliveryAddressDto;
import ru.grabovsky.cartservice.services.CartService;

import javax.annotation.Generated;
import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-04T14:18:29.004242+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.cartService.base-path:/cart-service/api/v1}")
public class CartApiController implements CartApi {

    private final CartApiDelegate delegate;

    private final CartService cartService;

    public CartApiController(@Autowired(required = false) CartApiDelegate delegate, CartService cartService) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CartApiDelegate() {});
        this.cartService = cartService;
    }

    @Override
    public CartApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<CartDto> getCart(String cartId) {
        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> addProductToCart(String cartId, CartItemDto cartItemDto) {
        return new ResponseEntity<>(cartService.addProductToCart(cartId, cartItemDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> deleteProductFromCart(String cartId, Long itemId) {
        return new ResponseEntity<>(cartService.deleteProductFromCartById(cartId, itemId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> getEmptyCart() {
        String cartId = UUID.randomUUID().toString();
        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> clearCart(String cartId) {
        cartService.clear(cartId);
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @Override
    public ResponseEntity<CartDto> createOrder(String cartId, DeliveryAddressDto addressDto) {
        return ResponseEntity.ok(cartService.createOrder(cartId, addressDto));
    }
}
