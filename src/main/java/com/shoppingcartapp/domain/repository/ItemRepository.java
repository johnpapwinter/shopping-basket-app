package com.shoppingcartapp.domain.repository;

import com.shoppingcartapp.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findItemByItemName(String itemName);

}
