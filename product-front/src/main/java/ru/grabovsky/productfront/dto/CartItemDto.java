package ru.grabovsky.productfront.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class CartItemDto {
    private Long id;
    private String cartId;
    private int count;
    private BigDecimal price;
    private ProductDto product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemDto that = (CartItemDto) o;
        return Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId());
    }
}
