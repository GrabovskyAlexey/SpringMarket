package ru.grabovsky.orderservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.orderservice.dto.OrderDto;
import ru.grabovsky.orderservice.entity.Order;

@Mapper(uses = OrderItemMapping.class)
public interface OrderMapper {
    @Mappings(value = {
            @Mapping(target = "userId", source = "user.id"),
    })
    OrderDto mapToDto(Order order);
}
