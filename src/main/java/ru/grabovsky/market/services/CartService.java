package ru.grabovsky.market.services;

import org.springframework.stereotype.Service;
import ru.grabovsky.market.exceptions.ResourceNotFoundException;
import ru.grabovsky.market.models.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<Product> cart;

    public CartService() {
        cart = new ArrayList<>();
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addProductToCart(Product product){
        cart.add(product);
    }

    public void deleteProductFromCartById(Long id) {
        Product product = cart
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product id=" + id + " not found in cart"));
        cart.remove(product);
    }
}
