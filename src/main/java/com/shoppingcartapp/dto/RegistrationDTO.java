package com.shoppingcartapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDTO {

    private String username;

    private String password;

    private Enum userRole;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
