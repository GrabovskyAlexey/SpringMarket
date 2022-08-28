package ru.grabovsky.productback.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.grabovsky.productback.dto.CategoryDto;
import ru.grabovsky.productback.models.Category;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    CategoryDto mapToDto(Category category);
    Category map(String name);
}
