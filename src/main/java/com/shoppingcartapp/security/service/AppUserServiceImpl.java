package com.shoppingcartapp.security.service;

import com.shoppingcartapp.security.dto.RegistrationDTO;
import com.shoppingcartapp.security.enums.RoleList;
import com.shoppingcartapp.security.model.AppRole;
import com.shoppingcartapp.security.model.AppUser;
import com.shoppingcartapp.security.repository.AppRoleRepository;
import com.shoppingcartapp.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
                              PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void addNewUser(RegistrationDTO registrationDTO) throws IllegalStateException {
        appUserRepository.findAppUserByUsername(registrationDTO.getUsername())
                .ifPresent(theUser -> {
                    throw new IllegalStateException("User already exists");
                });
        AppUser newAppUser = new AppUser(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword()));

        //user role hardcoded for now
        Optional<AppRole> appRole = appRoleRepository.findAppRoleByRoleNames(RoleList.USER);
        appRole.get().getAppUsers().add(newAppUser);
        newAppUser.getAppRoles().add(appRole.get());

        appUserRepository.save(newAppUser);
        appRoleRepository.save(appRole.get());
    }

    @Override
    @Transactional
    public Optional<AppUser> findUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }
}
