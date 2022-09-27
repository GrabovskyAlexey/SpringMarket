package ru.grabovsky.cartservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.cartservice.entity.User;
import ru.grabovsky.cartservice.repositories.UserRepository;

import javax.persistence.EntityExistsException;

/**
 * UserService
 *
 * @author grabovsky.alexey
 * @created 21.09.2022 13:01
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Long getUserIdByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
        return user.getId();
    }
}
