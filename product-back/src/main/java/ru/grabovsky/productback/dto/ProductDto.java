package ru.grabovsky.productback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * ProductDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-05T13:46:42.666337+03:00[Europe/Moscow]")
public class ProductDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("price")
  private BigDecimal price;

  @JsonProperty("rating")
  private BigDecimal rating;

  @JsonProperty("description")
  private String description;

  @JsonProperty("categoryName")
  private String categoryName;

  public ProductDto id(Long id) {
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

  public ProductDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", required = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProductDto price(java.math.BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", required = false)
  public java.math.BigDecimal getPrice() {
    return price;
  }

  public void setPrice(java.math.BigDecimal price) {
    this.price = price;
  }

  public ProductDto rating(java.math.BigDecimal rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
  */
  
  @Schema(name = "rating", required = false)
  public java.math.BigDecimal getRating() {
    return rating;
  }

  public void setRating(java.math.BigDecimal rating) {
    this.rating = rating;
  }

  public ProductDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductDto categoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  /**
   * Get categoryName
   * @return categoryName
  */
  
  @Schema(name = "categoryName", required = false)
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDto productDto = (ProductDto) o;
    return Objects.equals(this.id, productDto.id) &&
        Objects.equals(this.title, productDto.title) &&
        Objects.equals(this.price, productDto.price) &&
        Objects.equals(this.rating, productDto.rating) &&
        Objects.equals(this.description, productDto.description) &&
        Objects.equals(this.categoryName, productDto.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, price, rating, description, categoryName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
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

