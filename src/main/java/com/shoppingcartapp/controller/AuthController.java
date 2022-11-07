package com.shoppingcartapp.controller;

import com.shoppingcartapp.security.dto.RegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String viewRegistrationPage(Model model) {
        model.addAttribute("userRegistration", new RegistrationDTO());
        return "registration";
    }
}
