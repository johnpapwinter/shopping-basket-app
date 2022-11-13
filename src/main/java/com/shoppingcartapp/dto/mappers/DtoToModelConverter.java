package com.shoppingcartapp.dto.mappers;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.model.Item;

public class DtoToModelConverter {

    private DtoToModelConverter() {

    }

    public static Item dtoToModel(Item item, ItemDTO itemDTO) {

        item.setItemName(itemDTO.getItemName());
        item.setItemCost(itemDTO.getItemCost());
        item.setQuantity(itemDTO.getQuantity());

        return item;
    }
}
