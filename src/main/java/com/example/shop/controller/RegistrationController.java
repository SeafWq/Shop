package com.example.shop.controller;


import com.example.shop.registration.RegistrationRequest;
import com.example.shop.registration.RegistrationService;
import com.example.shop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String register(Errors errors, RegistrationRequest request){
        if (errors.hasErrors()){
            return "registration";
        }
         registrationService.register(request);
         return "redirect:/login";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }
}
