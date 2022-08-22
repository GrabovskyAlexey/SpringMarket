package ru.grabovsky.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.dto.MessageDto;
import ru.grabovsky.market.dto.ProductDto;
import ru.grabovsky.market.exceptions.ResourceNotFoundException;
import ru.grabovsky.market.exceptions.ValidationErrorException;
import ru.grabovsky.market.mappers.ProductMapper;
import ru.grabovsky.market.models.Product;
import ru.grabovsky.market.services.ProductService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("")
    public Page<ProductDto> getAllProducts(@RequestParam(required = false) Integer min,
                                           @RequestParam(required = false) Integer max,
                                           @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findWithMinOrMaxPrice(min, max, pageIndex).map(productMapper::mapToDto);
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
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
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
