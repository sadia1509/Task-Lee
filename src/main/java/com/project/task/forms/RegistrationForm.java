package com.project.task.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationForm {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;
}
