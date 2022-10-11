package com.example.shop.controller;


import com.example.shop.registration.RegistrationRequest;
import com.example.shop.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public String register(){
        return "registration";
    }

    @PostMapping()
    public String register(RegistrationRequest request){
         registrationService.register(request);
         return "redirect:/login";
    }
}
