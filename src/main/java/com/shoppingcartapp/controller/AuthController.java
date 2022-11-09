package com.shoppingcartapp.controller;

import com.shoppingcartapp.security.dto.RegistrationDTO;
import com.shoppingcartapp.security.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

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
    public String registerNewUser(@ModelAttribute("registrationData") @Valid RegistrationDTO registrationDTO,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("registrationData", registrationDTO);
            return "registration";
        }

        if(!Objects.equals(registrationDTO.getPassword(), registrationDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "Passwords do not match", "Passwords do not match");
            model.addAttribute("registrationData", registrationDTO);
            return "registration";
        }

        appUserService.addNewUser(registrationDTO);
        return "redirect:/login";
    }
}
