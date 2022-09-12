/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ru.grabovsky.cartservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.grabovsky.cartservice.dto.CartDto;
import ru.grabovsky.cartservice.dto.CartItemDto;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T12:09:37.809503+03:00[Europe/Moscow]")
@Validated
@Tag(name = "cart", description = "the cart API")
public interface CartApi {

    default CartApiDelegate getDelegate() {
        return new CartApiDelegate() {};
    }

    /**
     * PUT /cart/{cartId} : Add product in cart
     *
     * @param cartId Cart id (required)
     * @param cartItemDto Cart Item (required)
     * @return Successfully add product to cart (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
        operationId = "addProductToCart",
        summary = "Add product in cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully add product to cart", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/cart/{cartId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<CartDto> addProductToCart(
        @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
        @Parameter(name = "CartItemDto", description = "Cart Item", required = true) @Valid @RequestBody CartItemDto cartItemDto
    ) {
        return getDelegate().addProductToCart(cartId, cartItemDto);
    }


    /**
     * DELETE /cart/{cartId}/{itemId} : Delete item from cart
     *
     * @param cartId Cart id (required)
     * @param itemId Item id (required)
     * @return Cart without deleted item (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
        operationId = "deleteProductFromCart",
        summary = "Delete item from cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Cart without deleted item", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/cart/{cartId}/{itemId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> deleteProductFromCart(
        @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
        @Parameter(name = "itemId", description = "Item id", required = true) @PathVariable("itemId") Long itemId
    ) {
        return getDelegate().deleteProductFromCart(cartId, itemId);
    }


    /**
     * GET /cart/{cartId} : List of products in cart
     *
     * @param cartId Cart id (required)
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
        operationId = "getCart",
        summary = "List of products in cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "User cart", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/cart/{cartId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> getCart(
        @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId
    ) {
        return getDelegate().getCart(cartId);
    }


    /**
     * GET /cart : Get empty cart
     *
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
        operationId = "getEmptyCart",
        summary = "Get empty cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "User cart", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/cart",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> getEmptyCart(
        
    ) {
        return getDelegate().getEmptyCart();
    }

    /**
     * GET /cart/clear : clear cart
     *
     * @return User cart (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
            operationId = "clearCart",
            summary = "Clear cart",
            tags = { "cart" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "User cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/cart/{cartId}/clear",
            produces = { "application/json" }
    )
    default ResponseEntity<CartDto> clearCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId
    ) {
        return getDelegate().clearCart();
    }

}
