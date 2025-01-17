package com.example.G_Books.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = " firstname is mandatory ")
    @NotBlank(message = " firstname is mandatory ")
    private String firstname;
    @NotEmpty(message = " lastname is mandatory ")
    @NotBlank(message = " lastname is mandatory ")
    private String lastname;
    @Email(message = " Email is not formatted ")
    @NotEmpty(message = " email is mandatory ")
    @NotBlank(message = " email is mandatory ")
    private String email;
    @NotEmpty(message = " password is mandatory ")
    @NotBlank(message = " password is mandatory ")
    @Size(min = 8 , message = " Password should be 8 charachter minimum ")
    private String password;
}


