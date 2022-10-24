package com.shoppingcartapp.service;

import com.shoppingcartapp.dto.EmailDTO;

public interface EmailService {

    String sendListEmail(EmailDTO emailDTO);
}
