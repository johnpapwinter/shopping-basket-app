package com.shoppingcartapp.controller;

import com.shoppingcartapp.security.dto.RegistrationDTO;
import com.shoppingcartapp.security.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AppUserServiceImpl appUserService;

    @Autowired
    public AuthController(AppUserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String viewRegistrationPage(Model model) {
        model.addAttribute("registrationData", new RegistrationDTO());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registerNewUser(@ModelAttribute("registrationData") RegistrationDTO registrationDTO) {
        appUserService.addNewUser(registrationDTO);
        return "redirect:/login";
    }
}
