package com.shoppingcartapp.controller;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.model.Item;
import com.shoppingcartapp.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private final ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    public ItemController(ShoppingCartServiceImpl shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
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
}
