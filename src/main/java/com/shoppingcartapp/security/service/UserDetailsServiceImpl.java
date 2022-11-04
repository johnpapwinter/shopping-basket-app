package com.shoppingcartapp.security.service;

import com.shoppingcartapp.security.model.AppUser;
import com.shoppingcartapp.security.model.UserDetailsImpl;
import com.shoppingcartapp.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow();
        return UserDetailsImpl.buildUser(appUser);
    }
}
