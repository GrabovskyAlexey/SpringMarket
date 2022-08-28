package ru.grabovsky.productfront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.grabovsky.productfront.dto.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final RestTemplate restTemplate;
    @Value("${url.product}")
    private String url;

    public ProductDto findById(Long id) {
        return restTemplate.getForEntity(url + "/" + id, ProductDto.class).getBody();
    }

    public void save(ProductDto product) {
        restTemplate.postForLocation(url, product);
    }


    public void updateProductFromDto(ProductDto productDto) {
        restTemplate.put(url, productDto);
    }

    public void deleteById(Long id) {
        restTemplate.delete(url + "/" + id);
    }

    public List<ProductDto> findAll() {
        return restTemplate.getForEntity(url, List.class).getBody();
    }
}
