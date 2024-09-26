package com.project.task.controllers.ui;

import com.project.task.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/web")
public class UserUIController {

    @Autowired
    private PageService pageService;

    @GetMapping("/users")
    public String users() {
        return "users";
    }
}
