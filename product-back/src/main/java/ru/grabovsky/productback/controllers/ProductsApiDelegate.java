package ru.grabovsky.productback.controllers;

import ru.grabovsky.productback.dto.MessageDto;
import ru.grabovsky.productback.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link ProductsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T13:33:16.395512+03:00[Europe/Moscow]")
public interface ProductsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /products : Add product
     *
     * @param productDto Product Item (required)
     * @return Successfully add product (status code 201)
     *         or Bad Request (status code 400)
     * @see ProductsApi#addProduct
     */
    default ResponseEntity<Void> addProduct(ProductDto productDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /products/{id} : Delete product
     *
     * @param id product id (required)
     * @return Successfully delete product (status code 200)
     * @see ProductsApi#deleteProduct
     */
    default ResponseEntity<MessageDto> deleteProduct(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"date\" : \"2000-01-23\", \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /products : Get all products
     *
     * @return list of all products (status code 200)
     *         or Bad Request (status code 400)
     * @see ProductsApi#getAllProducts
     */
    default ResponseEntity<List<ProductDto>> getAllProducts() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"price\" : 6.027456183070403, \"rating\" : 1.4658129805029452, \"description\" : \"description\", \"id\" : 0, \"title\" : \"title\", \"categoryName\" : \"categoryName\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /products/{id} : Get product by id
     *
     * @param id product id (required)
     * @return Get one products (status code 200)
     *         or Bad Request (status code 400)
     *         or Not found product (status code 404)
     * @see ProductsApi#getProductById
     */
    default ResponseEntity<ProductDto> getProductById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"price\" : 6.027456183070403, \"rating\" : 1.4658129805029452, \"description\" : \"description\", \"id\" : 0, \"title\" : \"title\", \"categoryName\" : \"categoryName\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /products : Update product
     *
     * @param productDto Product Item (required)
     * @return Successfully add product (status code 201)
     *         or Bad Request (status code 400)
     * @see ProductsApi#updateProduct
     */
    default ResponseEntity<Void> updateProduct(ProductDto productDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
