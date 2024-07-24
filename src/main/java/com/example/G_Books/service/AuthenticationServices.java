package com.example.G_Books.service;

import com.example.G_Books.entity.Token;
import com.example.G_Books.entity.User;
import com.example.G_Books.repository.RoleDao;
import com.example.G_Books.repository.TokenDao;
import com.example.G_Books.repository.UserDao;
import com.example.G_Books.request.EmailTemplateName;
import com.example.G_Books.request.RegistrationRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {

    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final TokenDao tokenDao;
    private final EmailService emailService;
    @Value("${application.mailing.frontend.actication-url}")
    private String activateUrl;

    public void register(RegistrationRequest registrationRequest) throws MessagingException {

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

    private void sendValidationEmail(User user) throws MessagingException {

        var newToken = generateAndSaveActionToken(user);
        emailService.sendEmail(user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activateUrl,
                newToken,"Account activation");
    }

    private String generateAndSaveActionToken(User user){

        String generatedToken = generateAndSaveActionCode(6);
        var token = Token.builder().token(generatedToken).createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .users(user)
                .build();
        tokenDao.save(token);
        return generatedToken;
    }

    private String generateAndSaveActionCode(int length) {

        String charachter = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0 ; i  < length ; i++ ){
            int randomIndex = secureRandom.nextInt(charachter.length());
            codeBuilder.append(charachter.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
