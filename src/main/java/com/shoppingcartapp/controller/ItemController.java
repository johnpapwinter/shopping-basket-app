package com.shoppingcartapp.controller;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.dto.EmailDTO;
import com.shoppingcartapp.model.Item;
import com.shoppingcartapp.service.EmailServiceImpl;
import com.shoppingcartapp.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ItemController {

    private final ShoppingCartServiceImpl shoppingCartService;

    private final EmailServiceImpl emailService;

    @Autowired
    public ItemController(ShoppingCartServiceImpl shoppingCartService, EmailServiceImpl emailService) {
        this.shoppingCartService = shoppingCartService;
        this.emailService = emailService;
    }


    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/basket")
    public String viewBasket(Model model) {
        model.addAttribute("items", shoppingCartService.findAllItems());
        return "basket";
    }

    @GetMapping("/about")
    public String viewAboutPage() {
        return "about";
    }

    @GetMapping("/add")
    public String addItemPage(Model model) {
        model.addAttribute("formData", new ItemDTO());
        return "add-item";
    }

    @PostMapping("/add/save")
    public String addItem(@ModelAttribute("formData") ItemDTO itemDTO) {
        shoppingCartService.createItem(itemDTO);
        return "redirect:/basket";
    }

    @GetMapping("/edit/{itemName}")
    public String editItemPage(Model model, @PathVariable("itemName") String itemName) {
        Item item = shoppingCartService.findItemByName(itemName).get();
        model.addAttribute("item", item);
        return "edit-item";
    }

    @PostMapping("/edit/{itemName}")
    public String updateItem(@PathVariable("itemName") String itemName, @ModelAttribute("formData") ItemDTO itemDTO) {
        shoppingCartService.updateItem(itemName, itemDTO);
        return "redirect:/basket";
    }

    @GetMapping("/delete/{itemName}")
    public String deleteItem(@PathVariable("itemName") String itemName) {
        shoppingCartService.deleteItem(itemName);
        return "redirect:/basket";
    }

    @GetMapping("/email")
    public String emailPage(Model model) {
        model.addAttribute("emailData", new EmailDTO());
        return "email-page";
    }

    @PostMapping("/email/{emailAddress}")
    public String emailList(@PathVariable("emailAddress") String emailAddress, EmailDTO email) {
        email.setRecipient(emailAddress);
        email.setSubject("Shopping List");
        email.setMsgBody(emailService.listBeautifier(shoppingCartService.findAllItems()));
        emailService.sendListEmail(email);

        return "redirect:/basket";
    }
}
