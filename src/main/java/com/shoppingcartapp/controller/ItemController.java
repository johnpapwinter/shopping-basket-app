package com.shoppingcartapp.controller;

import com.shoppingcartapp.dto.ItemDTO;
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
        List<ItemDTO> items = new ArrayList<>();
        ItemDTO itemDTO1 = new ItemDTO("Book", 10.1F, 2);
        ItemDTO itemDTO2 = new ItemDTO("Laptop", 20.0F, 3);
        items.add(itemDTO1);
        items.add(itemDTO2);

        model.addAttribute("items", items);
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

    @GetMapping("/edit")
    public String editItemPage() {
        return "edit-item";
    }

    @PostMapping("/add/save")
    public String addItem(@ModelAttribute("formData") ItemDTO itemDTO) {
        shoppingCartService.createItem(itemDTO);
        return "redirect:/basket";
    }


}
