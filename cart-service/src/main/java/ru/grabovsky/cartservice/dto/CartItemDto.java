package ru.grabovsky.cartservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * CartItemDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-03T21:03:48.477960+03:00[Europe/Moscow]")
public class CartItemDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("cartId")
  private String cartId;

  @JsonProperty("count")
  private Long count;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("productName")
  private String productName;

  @JsonProperty("productId")
  private Long productId;

  public CartItemDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CartItemDto cartId(String cartId) {
    this.cartId = cartId;
    return this;
  }

  /**
   * Get cartId
   * @return cartId
  */
  
  @Schema(name = "cartId", required = false)
  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  public CartItemDto count(Long count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  
  @Schema(name = "count", required = false)
  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public CartItemDto price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", required = false)
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public CartItemDto productName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * Get productName
   * @return productName
  */
  
  @Schema(name = "productName", required = false)
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public CartItemDto productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  */
  
  @Schema(name = "productId", required = false)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartItemDto cartItemDto = (CartItemDto) o;
    return Objects.equals(this.id, cartItemDto.id) &&
        Objects.equals(this.cartId, cartItemDto.cartId) &&
        Objects.equals(this.count, cartItemDto.count) &&
        Objects.equals(this.price, cartItemDto.price) &&
        Objects.equals(this.productName, cartItemDto.productName) &&
        Objects.equals(this.productId, cartItemDto.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cartId, count, price, productName, productId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartItemDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cartId: ").append(toIndentedString(cartId)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
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
}

