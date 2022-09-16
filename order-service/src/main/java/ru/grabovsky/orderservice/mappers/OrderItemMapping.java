package ru.grabovsky.orderservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.orderservice.dto.OrderItemDto;
import ru.grabovsky.orderservice.entity.OrderItem;

@Mapper
public interface OrderItemMapping {
    @Mappings(
            @Mapping(target = "productId", source = "product.id")
    )
    OrderItemDto mapToDto(OrderItem orderItem);
}
