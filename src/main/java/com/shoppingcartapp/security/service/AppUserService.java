package com.shoppingcartapp.security.service;

import com.shoppingcartapp.dto.RegistrationDTO;
import com.shoppingcartapp.security.model.AppUser;

import java.util.Optional;

public interface AppUserService {

    void addNewUser(RegistrationDTO registrationDTO);

    Optional<AppUser> findUserByUsername(String username);
}
