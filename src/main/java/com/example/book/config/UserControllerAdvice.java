package com.example.book.config;

import com.example.book.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Component
public class UserControllerAdvice {

    private final UserRepository userRepository;

    public UserControllerAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addUserToModel(Model model,
                               Authentication authentication
    ) {
        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails) {
            try {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userRepository.findByEmail(userDetails.getUsername())
                        .ifPresent(user -> model.addAttribute("currentUser", user));

            } catch (Exception e) {
            }
        }
    }
}