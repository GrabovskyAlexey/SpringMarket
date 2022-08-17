package ru.grabovsky.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.dto.CartItemDto;
import ru.grabovsky.market.models.OrderItem;

@Mapper(injectionStrategy = InjectionStrategy.FIELD)
public interface OrderItemMapper {
    @Mappings(value = {
            @Mapping(target = "product", source = "product")
    })
    OrderItem mapFromDto(CartItemDto item);
}
