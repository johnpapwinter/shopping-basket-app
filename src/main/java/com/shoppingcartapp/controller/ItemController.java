package com.shoppingcartapp.controller;

import com.shoppingcartapp.model.Item;
import com.shoppingcartapp.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    @Autowired
    public ItemController() {
    }

    // GET ALL
    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/basket")
    public String viewBasket() {
        return "basket";
    }

    @GetMapping("/about")
    public String viewAboutPage() {
        return "about";
    }

    @GetMapping("/add")
    public String addItemPage() {
        return "add-item";
    }

    @GetMapping("/edit")
    public String editItemPage() {
        return "edit-item";
    }



}
