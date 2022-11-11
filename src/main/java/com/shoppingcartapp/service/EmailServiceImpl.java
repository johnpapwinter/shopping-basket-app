package com.shoppingcartapp.service;

import com.shoppingcartapp.dto.EmailDTO;
import com.shoppingcartapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String emailShoppingList(EmailDTO emailDTO) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDTO.getRecipient());
            mailMessage.setText(emailDTO.getMsgBody());
            mailMessage.setSubject(emailDTO.getSubject());
            javaMailSender.send(mailMessage);
            return "Sent";
        } catch (Exception e) {
            return "Error";
        }
    }

    public String prepareEmailBody(List<Item> itemList, String message) {
        String basket = "";
        float totalCost = 0.0F;
        for(Item item : itemList) {
            basket = basket + " " + item.getItemName()
            + " | cost: " + item.getItemCost() + " | quantity: " + item.getQuantity() + "\n";
            totalCost += item.getItemCost();
        }
        return basket + "\nTotal Cost: " + totalCost + "\n\n" + message;
    }
}
