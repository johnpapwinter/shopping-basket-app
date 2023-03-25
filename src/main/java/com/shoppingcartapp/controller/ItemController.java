package com.shoppingcartapp.controller;

import com.shoppingcartapp.domain.dto.ItemDTO;
import com.shoppingcartapp.domain.dto.EmailDTO;
import com.shoppingcartapp.domain.model.Item;
import com.shoppingcartapp.service.EmailServiceImpl;
import com.shoppingcartapp.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class ItemController {

    private final ShoppingCartServiceImpl shoppingCartService;

    private final EmailServiceImpl emailService;

    @Autowired
    public ItemController(ShoppingCartServiceImpl shoppingCartService, EmailServiceImpl emailService) {
        this.shoppingCartService = shoppingCartService;
        this.emailService = emailService;
    }


    @GetMapping("/home")
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
    public String viewAddItemPage(Model model) {
        model.addAttribute("formData", new ItemDTO());
        return "add-item";
    }

    @PostMapping("/add/save")
    public String addItem(@ModelAttribute("formData") ItemDTO itemDTO) {
        shoppingCartService.createItem(itemDTO);
        return "redirect:/basket";
    }

    @GetMapping("/edit/{itemName}")
    public String viewEditItemPage(Model model, @PathVariable("itemName") String itemName) {
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
    public String viewEmailPage(Model model) {
        model.addAttribute("emailData", new EmailDTO());
        return "email-page";
    }

    @PostMapping("/email/{emailAddress}")
    public String emailShoppingList(@PathVariable("emailAddress") String emailAddress, EmailDTO email) {
        email.setMsgBody(emailService.prepareEmailBody(shoppingCartService.findAllItems(), email.getMsgBody()));
//        emailService.emailShoppingList(email);
        emailService.emailListWithAttachedExcel(email);

        return "redirect:/basket";
    }

    @GetMapping("/confirmation")
    public String viewEmptyBasketConfirmationPage() {
        return "confirmation-page";
    }

    @GetMapping("/confirmation/remove")
    public String emptyBasket() {
        shoppingCartService.deleteAllItems();
        return "redirect:/home";
    }

    @GetMapping("/xls")
    public String exportToXls() throws IOException {
        shoppingCartService.exportToExcel();

        return "redirect:/basket";
    }

}
