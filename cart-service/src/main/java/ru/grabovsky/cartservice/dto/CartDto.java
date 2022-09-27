package ru.grabovsky.cartservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CartDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-03T21:03:48.477960+03:00[Europe/Moscow]")
public class CartDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("cart")
  @Valid
  private List<CartItemDto> cart = null;

  public CartDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CartDto cart(List<CartItemDto> cart) {
    this.cart = cart;
    return this;
  }

  public CartDto addCartItem(CartItemDto cartItem) {
    if (this.cart == null) {
      this.cart = new ArrayList<>();
    }
    this.cart.add(cartItem);
    return this;
  }

  /**
   * Get cart
   * @return cart
  */
  @Valid 
  @Schema(name = "cart", required = false)
  public List<CartItemDto> getCart() {
    return cart;
  }

  public void setCart(List<CartItemDto> cart) {
    this.cart = cart;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartDto cartDto = (CartDto) o;
    return Objects.equals(this.id, cartDto.id) &&
        Objects.equals(this.cart, cartDto.cart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cart);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cart: ").append(toIndentedString(cart)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  public void clear(){
    cart.clear();
  }
}

