package ru.grabovsky.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressDto {
    private Long id;
    private String country;
    private String city;
    private String region;
    private String street;
    private String house;
    private Integer flat;
}
