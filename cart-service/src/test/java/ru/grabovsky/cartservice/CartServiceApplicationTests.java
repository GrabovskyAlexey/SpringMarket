package ru.grabovsky.cartservice;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import ru.grabovsky.cartservice.dto.CartDto;
import ru.grabovsky.cartservice.dto.CartItemDto;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartServiceApplicationTests extends FunctionalTest {


    @Test
    void testGetCart() {

        CartDto cart = createRequest()
                .url("/cart-service/api/v1/cart/test-cart")
                .get(new ParameterizedTypeReference<>() {});

        String expected = getResource("testGetCart/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                cart,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testAddProductToCart() {
        clearCart();
        CartItemDto item = new CartItemDto();
        item.setCartId("test-cart");
        item.setId(1L);
        item.setProductId(2L);
        item.setCount(5L);
        item.setPrice(95.01);
        item.setProductName("Milk");
        HttpEntity<CartItemDto> entity = new HttpEntity<>(item);
        CartDto cart = createRequest()
                .url("/cart-service/api/v1/cart/test-cart")
                .put(new ParameterizedTypeReference<>() {}, entity);

        String expected = getResource("testAddProductToCart/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                cart,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testDeleteProductFromCart() {
        clearCart();
        CartItemDto itemOne = new CartItemDto();
        itemOne.setCartId("test-cart");
        itemOne.setId(1L);
        itemOne.setProductId(2L);
        itemOne.setCount(5L);
        itemOne.setPrice(95.01);
        itemOne.setProductName("Milk");
        CartItemDto itemTwo = new CartItemDto();
        itemTwo.setCartId("test-cart");
        itemTwo.setId(2L);
        itemTwo.setProductId(4L);
        itemTwo.setCount(2L);
        itemTwo.setPrice(12.05);
        itemTwo.setProductName("Bread");
        HttpEntity<CartItemDto> entity = new HttpEntity<>(itemOne);
        createRequest()
                .url("/cart-service/api/v1/cart/test-cart")
                .put(new ParameterizedTypeReference<>() {}, entity);
        entity = new HttpEntity<>(itemTwo);
        createRequest()
                .url("/cart-service/api/v1/cart/test-cart")
                .put(new ParameterizedTypeReference<>() {}, entity);

        CartDto cart = createRequest()
                .url("/cart-service/api/v1/cart/test-cart/1")
                .delete(new ParameterizedTypeReference<>() {});

        String expected = getResource("testDeleteProductFromCart/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                cart,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    private void clearCart() {
        createRequest()
                .url("/cart-service/api/v1/cart/test-cart/clear")
                .get(new ParameterizedTypeReference<>() {});
    }

}
