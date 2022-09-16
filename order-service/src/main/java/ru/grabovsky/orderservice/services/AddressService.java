package ru.grabovsky.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.orderservice.entity.DeliveryAddress;
import ru.grabovsky.orderservice.repositories.AddressRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public DeliveryAddress findById(Long id){
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
