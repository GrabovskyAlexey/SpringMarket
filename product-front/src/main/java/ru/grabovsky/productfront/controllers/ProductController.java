package ru.grabovsky.productfront.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.productfront.dto.MessageDto;
import ru.grabovsky.productfront.dto.ProductDto;
import ru.grabovsky.productfront.exceptions.ValidationErrorException;
import ru.grabovsky.productfront.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public List<ProductDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return  productService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationErrorException(
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())
            );
        }
        System.out.println(productDto);
        productService.save(productDto);
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
