package ru.grabovsky.cartservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryAddressDto {
    private Long id;
    private String country;
    private String city;
    private String region;
    private String street;
    private String house;
    private Integer flat;
}
