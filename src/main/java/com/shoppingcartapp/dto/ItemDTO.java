package com.shoppingcartapp.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class ItemDTO {

    private String itemName;

    private Float itemCost;

    private Integer quantity;

    public ItemDTO() {
    }

    public ItemDTO(String itemName, Float itemCost, Integer quantity) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.quantity = quantity;
    }
}
