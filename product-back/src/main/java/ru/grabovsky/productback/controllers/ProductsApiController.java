package ru.grabovsky.productback.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grabovsky.productback.dto.MessageDto;
import ru.grabovsky.productback.dto.ProductDto;
import ru.grabovsky.productback.mappers.ProductMapper;
import ru.grabovsky.productback.services.ProductService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import javax.persistence.EntityNotFoundException;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T13:24:58.033551+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.cartService.base-path:/prod-service/api/v1}")
public class ProductsApiController implements ProductsApi {

    private final ProductsApiDelegate delegate;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductsApiController(@Autowired(required = false) ProductsApiDelegate delegate, ProductService productService, ProductMapper productMapper) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ProductsApiDelegate() {});
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ProductsApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        return ResponseEntity.ok(productMapper.mapToDto(
                productService.findById(id).orElseThrow(() -> new EntityNotFoundException())
        ));
    }

    @Override
    public ResponseEntity<Void> addProduct(ProductDto productDto) {
        productService.save(productMapper.mapFromDto(productDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MessageDto> deleteProduct(Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok(new MessageDto().message("Продукт успешно удален").date(LocalDate.from(Instant.now())));
    }

    @Override
    public ResponseEntity<Void> updateProduct(ProductDto productDto) {
        productService.updateProductFromDto(productDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
