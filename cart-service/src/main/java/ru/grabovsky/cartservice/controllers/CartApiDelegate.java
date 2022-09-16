package ru.grabovsky.cartservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ru.grabovsky.cartservice.dto.CartDto;
import ru.grabovsky.cartservice.dto.CartItemDto;
import ru.grabovsky.cartservice.dto.DeliveryAddressDto;


import javax.annotation.Generated;
import java.util.Optional;

/**
 * A delegate to be called by the {@link CartApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T12:09:37.809503+03:00[Europe/Moscow]")
public interface CartApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PUT /cart/{cartId} : Add product in cart
     *
     * @param cartId Cart id (required)
     * @param cartItemDto Cart Item (required)
     * @return Successfully add product to cart (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#addProductToCart
     */
    default ResponseEntity<CartDto> addProductToCart(String cartId,
                                                     CartItemDto cartItemDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /cart/{cartId}/{itemId} : Delete item from cart
     *
     * @param cartId Cart id (required)
     * @param itemId Item id (required)
     * @return Cart without deleted item (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#deleteProductFromCart
     */
    default ResponseEntity<CartDto> deleteProductFromCart(String cartId,
        Long itemId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /cart/{cartId} : List of products in cart
     *
     * @param cartId Cart id (required)
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#getCart
     */
    default ResponseEntity<CartDto> getCart(String cartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /cart : Get empty cart
     *
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#getEmptyCart
     */
    default ResponseEntity<CartDto> getEmptyCart() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /cart/{cartId}/clear : Clear cart
     *
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#clearCart
     */
    default ResponseEntity<CartDto> clearCart() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /cart/{cartId}/order : Create order
     *
     * @param cartId Cart id (required)
     * @param addressDto Address (required)
     * @return Successfully create order (status code 200)
     *         or Bad Request (status code 400)
     * @see CartApi#createOrder
     */
    default ResponseEntity<CartDto> createOrder(String cartId,
                                                     DeliveryAddressDto addressDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"cart\" : [ { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" }, { \"productId\" : 5, \"price\" : 1.4658129805029452, \"cartId\" : \"cartId\", \"count\" : 6, \"id\" : 0, \"productName\" : \"productName\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
