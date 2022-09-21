package ru.grabovsky.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.orderservice.entity.User;
import ru.grabovsky.orderservice.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

}
