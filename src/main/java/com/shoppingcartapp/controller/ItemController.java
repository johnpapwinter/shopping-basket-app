package com.shoppingcartapp.controller;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.model.Item;
import com.shoppingcartapp.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ItemController {

    private final ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    public ItemController(ShoppingCartServiceImpl shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // GET ALL
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allitemlist", shoppingCartService.findAllItems());
        return "index";
    }

    // POST
    @PostMapping("/add")
    public String addItem(@ModelAttribute("item") ItemDTO itemDTO) {
        shoppingCartService.createItem(itemDTO);
        return "redirect:/";
    }

    // PUT
    @GetMapping("/edit/{name}")
    public String editItem(@PathVariable(value = "name") String name, Model model) {
        Item item = shoppingCartService.findItemByName(name)
                .orElseThrow(() -> new RuntimeException());
        model.addAttribute("item", item);
        return "edit-item";
    }

    // DELETE


}
