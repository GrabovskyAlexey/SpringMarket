package ru.grabovsky.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.dto.ProductDto;
import ru.grabovsky.market.models.Product;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mappings(value = {
            @Mapping(target = "categoryName", source = "category.name")
    })
    ProductDto mapToDto(Product product);
}
