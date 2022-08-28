package ru.grabovsky.productback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.productback.dto.CategoryDto;
import ru.grabovsky.productback.mappers.CategoryMapper;
import ru.grabovsky.productback.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories().stream().map(categoryMapper::mapToDto).collect(Collectors.toList());
    }
}
