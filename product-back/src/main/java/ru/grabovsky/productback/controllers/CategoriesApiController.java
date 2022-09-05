package ru.grabovsky.productback.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grabovsky.productback.dto.CategoryDto;
import ru.grabovsky.productback.mappers.CategoryMapper;
import ru.grabovsky.productback.services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T13:24:58.033551+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.cartService.base-path:/prod-service/api/v1}")
public class CategoriesApiController implements CategoriesApi {

    private final CategoriesApiDelegate delegate;

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    public CategoriesApiController(@Autowired(required = false) CategoriesApiDelegate delegate, CategoryService categoryService, CategoryMapper categoryMapper) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CategoriesApiDelegate() {});
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoriesApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(
                categoryService.getAllCategories().stream()
                        .map(categoryMapper::mapToDto)
                        .collect(Collectors.toList())
        );
    }
}
