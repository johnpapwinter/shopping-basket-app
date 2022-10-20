package com.shoppingcartapp.dto.converters;

import com.shoppingcartapp.dto.ItemDTO;
import com.shoppingcartapp.model.Item;

public class DtoToModelConverter {

    private DtoToModelConverter() {

    }

    public static Item dtoToModel(Item item, ItemDTO itemDTO) {

        item.setItemName(itemDTO.getItemName());
        item.setItemCost(itemDTO.getItemCost());
        item.setQuantity(item.getQuantity());

        return item;
    }
}
