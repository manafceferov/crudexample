package com.example.book.service;

import com.example.book.entity.User;
import com.example.book.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String email,
                         String password,
                         String fullName
    ) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Bu email artıq mövcuddur!");
        }
        User user = new User(
                null,
                email,
                passwordEncoder.encode(password),
                fullName,
                "USER",
                true
        );
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı"));
    }
}
