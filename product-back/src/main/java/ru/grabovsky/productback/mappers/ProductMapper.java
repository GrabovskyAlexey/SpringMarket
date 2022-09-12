package ru.grabovsky.productback.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.productback.dto.ProductDto;
import ru.grabovsky.productback.models.Product;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @Mappings(value = {
            @Mapping(target = "categoryName", source = "category.name")
    })
    ProductDto mapToDto(Product product);
    @Mappings(value = {
            @Mapping(target = "category", source = "categoryName")
    })
    Product mapFromDto(ProductDto product);
}
