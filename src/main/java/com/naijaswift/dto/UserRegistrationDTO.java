package com.naijaswift.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRegistrationDTO{

    @NotBlank(message="First name is required")
    private String firstName;

    @NotBlank(message="Last name is required")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="phone number is required")
    @Size(min=11,max=15,message="Please phone number must be between 11 to 15 digits")
    private String phoneNumber;

    @NotBlank(message="Password ie required")
    @Size(min=8,message="Password must be at least 8 character long")
    private String password;

   @NotBlank(message="Transation pin is required")
   @Size(min=4,max=4,message="PIN must be exactly 4 digits")
    private String transactionPin;

   

}