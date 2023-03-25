package com.shoppingcartapp.service;

import com.shoppingcartapp.domain.dto.EmailDTO;
import com.shoppingcartapp.domain.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private ShoppingCartServiceImpl shoppingCartService;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender,
                            ShoppingCartServiceImpl shoppingCartService) {
        this.javaMailSender = javaMailSender;
        this.shoppingCartService = shoppingCartService;
    }

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

    @Override
    public String emailListWithAttachedExcel(EmailDTO emailDTO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(emailDTO.getRecipient());
            helper.setText(emailDTO.getMsgBody());
            helper.setSubject(emailDTO.getSubject());

            shoppingCartService.exportToExcel();
            FileSystemResource file = new FileSystemResource(
                    new File("src/main/resources/temp/list.xlsx")
            );
            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(message);
            return "Message sent";

        } catch (MessagingException e) {
            return "Error";
        } catch (IOException e) {
            throw new RuntimeException(e);
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
