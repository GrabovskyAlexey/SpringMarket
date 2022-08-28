package ru.grabovsky.productback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.productback.dto.MessageDto;
import ru.grabovsky.productback.dto.ProductDto;
import ru.grabovsky.productback.exceptions.ResourceNotFoundException;
import ru.grabovsky.productback.exceptions.ValidationErrorException;
import ru.grabovsky.productback.mappers.ProductMapper;
import ru.grabovsky.productback.models.Product;
import ru.grabovsky.productback.services.CategoryService;
import ru.grabovsky.productback.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping("")
    public List<ProductDto> getAllProducts() {
        return productService.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.mapToDto(
                productService
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found")
                        )
        );
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationErrorException(
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())
            );
        }
        Product product = productMapper.mapFromDto(productDto);
        product.setCategory(categoryService.findByName(productDto.getCategoryName()));
        productService.save(product);
    }

    @PutMapping("")
    public void update(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationErrorException(
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())
            );
        }
        productService.updateProductFromDto(productDto);
    }

    @DeleteMapping("/{id}")
    public MessageDto deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return new MessageDto("Продукт удален");
    }
}
