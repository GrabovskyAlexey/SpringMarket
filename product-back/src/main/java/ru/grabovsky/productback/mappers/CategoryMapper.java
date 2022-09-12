package ru.grabovsky.productback.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grabovsky.productback.dto.CategoryDto;
import ru.grabovsky.productback.exceptions.ResourceNotFoundException;
import ru.grabovsky.productback.models.Category;
import ru.grabovsky.productback.repositories.CategoryRepository;

@Mapper
public abstract class CategoryMapper{
    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public abstract CategoryDto mapToDto(Category category);

    public Category map(String name){
        return categoryRepository.findByName(name).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Category %s not found", name)));
    }
}
