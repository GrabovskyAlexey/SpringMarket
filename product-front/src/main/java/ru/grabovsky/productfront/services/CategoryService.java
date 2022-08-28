package ru.grabovsky.productfront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.grabovsky.productfront.dto.CategoryDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final RestTemplate restTemplate;

    @Value("${url.category}")
    private String url;

    public List<CategoryDto> getAllCategories(){
        return restTemplate.getForEntity(url, List.class).getBody();
    }
}
