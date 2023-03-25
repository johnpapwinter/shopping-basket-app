package com.shoppingcartapp.domain.dto.mappers;

import com.shoppingcartapp.domain.dto.ItemDTO;
import com.shoppingcartapp.domain.model.Item;

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
