package com.shoppingcartapp.model;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;

    @Column(name = "ITEM_COST")
    private Float itemCost;

    @Column(name = "ITEM_QUANTITY")
    private Integer quantity;



}
