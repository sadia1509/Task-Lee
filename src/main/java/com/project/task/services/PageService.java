package com.project.task.services;

import com.project.task.forms.RegistrationForm;
import com.project.task.models.User;
import com.project.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    private UserRepository userRepository;
    public void save(RegistrationForm registrationForm) {
        User user = User.builder()
                .name(registrationForm.getName())
                .email(registrationForm.getEmail())
                .password(registrationForm.getPassword())
                .phoneNumber(registrationForm.getPhoneNumber())
                .about(registrationForm.getAbout())
                .profilePictureLink("static/images/default-profile-picture.jpg")
                .build();
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
    }
}
