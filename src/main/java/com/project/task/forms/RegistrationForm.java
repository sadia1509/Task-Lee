package com.project.task.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters are required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8, message = "Minimum 8 characters are required")
    private String password;
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    @Size(max = 1000, message = "Maximum 1000 characters are allowed")
    private String about;
}
