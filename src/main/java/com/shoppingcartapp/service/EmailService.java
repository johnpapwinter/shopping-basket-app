package com.shoppingcartapp.service;

import com.shoppingcartapp.domain.dto.EmailDTO;

public interface EmailService {

    String emailShoppingList(EmailDTO emailDTO);
}
