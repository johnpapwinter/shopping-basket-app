package com.shoppingcartapp.service;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.exceptions.ItemAlreadyExistsException;
import com.shoppingcartapp.exceptions.ItemDoesNotExistException;
import com.shoppingcartapp.model.Item;
import com.shoppingcartapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.shoppingcartapp.dto.converters.DtoToModelConverter.dtoToModel;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ItemRepository itemRepository;


    @Autowired
    public ShoppingCartServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    @Transactional
    public Optional<Item> findItemByName(String itemName) {
        return itemRepository.findItemByItemName(itemName);
    }


    @Override
    @Transactional
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }


    @Override
    @Transactional
    public void updateItem(String itemName, ItemDTO itemDTO) throws ItemDoesNotExistException {
        Item found = itemRepository.findItemByItemName(itemName)
                .orElseThrow(() -> new ItemDoesNotExistException("Item does not exist"));

        itemRepository.save(dtoToModel(found, itemDTO));
    }


    @Override
    @Transactional
    public void createItem(ItemDTO itemDTO) throws ItemAlreadyExistsException {
        itemRepository.findItemByItemName(itemDTO.getItemName())
                .ifPresent(theItem -> {
                    throw new ItemAlreadyExistsException("Item already exists");
                });

        Item item = new Item();
        itemRepository.save(dtoToModel(item, itemDTO));
    }


    @Override
    @Transactional
    public void deleteItem(String itemName) throws ItemDoesNotExistException {
        Item found = itemRepository.findItemByItemName(itemName)
                .orElseThrow(() -> new ItemDoesNotExistException("Item does not exist"));

        itemRepository.delete(found);
    }
}
