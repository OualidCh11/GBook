package com.example.G_Books.webService;

import com.example.G_Books.request.RegistrationRequest;
import com.example.G_Books.service.AuthenticationServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationWS {

    private final AuthenticationServices authServices;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest registrationRequest
    ){
        authServices.register(registrationRequest);
        return ResponseEntity.accepted().build();
    }
}
