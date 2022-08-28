package ru.grabovsky.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grabovsky.market.dto.ProductDto;
import ru.grabovsky.market.exceptions.ResourceNotFoundException;
import ru.grabovsky.market.models.Product;
import ru.grabovsky.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int id) {
        return productRepository.findAll(PageRequest.of(id, 10));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto) {
        Product product = findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findWithMinOrMaxPrice(Integer min, Integer max, Integer pageIndex) {
        Pageable page = PageRequest.of(pageIndex - 1, 10);
        if (min == null && max == null) {
            return productRepository.findAll(page);
        } else if (min != null && max != null) {
            return productRepository.findProductByPriceBetween(min, max, page);
        } else if (min != null) {
            return productRepository.findProductByPriceAfter(min, page);
        } else {
            return productRepository.findProductByPriceBefore(max, page);
        }
    }
}
