package com.shoppingcartapp.security.dto;

import com.shoppingcartapp.security.enums.RoleList;
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
    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotNull
    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;

    private RoleList userRole;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
