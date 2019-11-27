package com.codegym.controllers;

import com.codegym.models.User;
import com.codegym.services.UserService;
import com.codegym.validation.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }
    @PostMapping("/validateUser")
    public String checkValidation(@Validated User user, BindingResult bindingResult, Model model) {
        new PhoneNumberValidator().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "index";
        }
        userService.save(user);
        model.addAttribute("message","Added user");
        return "index";
    }
}
