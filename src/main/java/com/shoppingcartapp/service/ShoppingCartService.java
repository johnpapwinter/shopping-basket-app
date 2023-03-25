package com.shoppingcartapp.service;

import com.shoppingcartapp.domain.dto.ItemDTO;
import com.shoppingcartapp.domain.model.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<Item> findItemByName(String itemName);

    List<Item> findAllItems();

    void updateItem(String itemName, ItemDTO itemDTO);

    void createItem(ItemDTO itemDTO);

    void deleteItem(String itemName);

    void deleteAllItems();

    void exportToExcel() throws IOException;
}
