package com.shoppingcartapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {

    private String itemName;

    private Float itemCost;

    private Integer quantity;
}
