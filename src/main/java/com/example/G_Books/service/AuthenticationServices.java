package com.example.G_Books.service;

import com.example.G_Books.entity.User;
import com.example.G_Books.repository.RoleDao;
import com.example.G_Books.repository.UserDao;
import com.example.G_Books.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {

    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public void register(RegistrationRequest registrationRequest){

        var userRole = roleDao.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() ->
                        new IllegalStateException("Rile User is not initialized"));
        var user = User.builder().firstname(registrationRequest.getFirstname()).lastname(registrationRequest.getLastname())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userDao.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user){
        var newToken = generateAndSaveActionToken(user);
    }

    private String generateAndSaveActionToken(User user){
        return null;
    }
}
