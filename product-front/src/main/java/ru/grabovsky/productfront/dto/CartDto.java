package ru.grabovsky.productfront.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private String id;
    private List<CartItemDto> cart;
}
