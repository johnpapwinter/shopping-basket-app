package com.shoppingcartapp.security.dto.converter;

import com.shoppingcartapp.security.dto.RegistrationDTO;
import com.shoppingcartapp.security.model.AppUser;

public class DtoToUserConverter {

    private DtoToUserConverter() {

    }

    public static AppUser dtoToUserConverter(RegistrationDTO dto) {
        AppUser appUser = new AppUser();

        appUser.setUsername(dto.getUsername());
        appUser.setPassword(dto.getPassword());


        return appUser;
    }
}
