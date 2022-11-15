package com.example.shop.controller;


import com.example.shop.registration.RegistrationRequest;
import com.example.shop.registration.RegistrationService;
import com.example.shop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public String register(){
        return "registration";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @PostMapping()
    public String register( @Valid @ModelAttribute("user") User user ,
                            @Valid Errors errors,
                            RegistrationRequest request, Model model){
        if (errors.hasErrors()){
            return "registration";
        }
        registrationService.register(request);
        return "redirect:/login";
    }
}
