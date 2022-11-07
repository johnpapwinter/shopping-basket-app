package com.shoppingcartapp.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDTO {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private String confirmPassword;

    private Enum userRole;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
