package com.project.task.controllers;

import com.project.task.enums.MessageType;
import com.project.task.forms.RegistrationForm;
import com.project.task.helpers.Message;
import com.project.task.services.PageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/web")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("userObject", registrationForm);
        return "registration";
    }

    @PostMapping("/register")
    public String processingRegistration(@Valid @ModelAttribute("userObject") RegistrationForm registrationForm,
                                         BindingResult bindingResult,
                                         HttpSession httpSession) {
        if (bindingResult.hasErrors()) return "registration";
        pageService.save(registrationForm);
        Message message = Message.builder()
                .text("Registration Successful")
                .messageType(MessageType.success)
                .build();
        httpSession.setAttribute("message", message);
        return "redirect:/web/registration";
    }

}
