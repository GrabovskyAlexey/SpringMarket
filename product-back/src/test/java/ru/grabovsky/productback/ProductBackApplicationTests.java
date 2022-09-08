package ru.grabovsky.productback;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.jdbc.Sql;
import ru.grabovsky.productback.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductBackApplicationTests extends FunctionalTest {

    @Test
    @Sql("db.sql")
    void getProductList(){

        List<ProductDto> products = createRequest()
                .url("/prod-service/api/v1/products")
                .get(new ParameterizedTypeReference<>() {});
        String expected = getResource("getProductList/expected.json");
        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Sql("db.sql")
    void getProductById(){
        ProductDto product = createRequest()
                .url("/prod-service/api/v1/products/1")
                .get(new ParameterizedTypeReference<>() {});
        String expected = getResource("getProductById/expected1.json");
        JsonAssert.assertJsonEquals(
                expected,
                product,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

        product = createRequest()
                        .url("/prod-service/api/v1/products/2")
                        .get(new ParameterizedTypeReference<>() {});
        expected = getResource("getProductById/expected2.json");
        JsonAssert.assertJsonEquals(
                expected,
                product,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Sql("db.sql")
    void addProduct(){
        ProductDto product = new ProductDto();
        product.setTitle("Cheese");
        product.setPrice(BigDecimal.valueOf(230.04));
        product.setRating(BigDecimal.valueOf(0));
        product.setDescription("Fresh Cheese");
        product.setCategoryName("Food");
        HttpEntity<ProductDto> entity = new HttpEntity<>(product);

        createRequest()
                .url("/prod-service/api/v1/products/")
                .post(new ParameterizedTypeReference<>() {}, entity);

        List<ProductDto> products = createRequest()
                .url("/prod-service/api/v1/products/")
                .get(new ParameterizedTypeReference<>() {});
        String expected = getResource("addProduct/expected.json");
        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Sql("db.sql")
    void updateProduct() {
        ProductDto product = createRequest()
                .url("/prod-service/api/v1/products/1")
                .get(new ParameterizedTypeReference<>() {
                });
        product.setDescription("Milk");
        HttpEntity<ProductDto> entity = new HttpEntity<>(product);

        createRequest()
                .url("/prod-service/api/v1/products/")
                .put(new ParameterizedTypeReference<>() {
                }, entity);

        List<ProductDto> products = createRequest()
                .url("/prod-service/api/v1/products/")
                .get(new ParameterizedTypeReference<>() {
                });
        String expected = getResource("updateProduct/expected.json");
        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Sql("db.sql")
    void deleteProduct() {
        createRequest()
                .url("/prod-service/api/v1/products/1")
                .delete(new ParameterizedTypeReference<>() {
                });

        List<ProductDto> products = createRequest()
                .url("/prod-service/api/v1/products/")
                .get(new ParameterizedTypeReference<>() {
                });
        String expected = getResource("deleteProduct/expected.json");
        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }
}
