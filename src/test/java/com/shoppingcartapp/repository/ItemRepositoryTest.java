package com.shoppingcartapp.repository;

import com.shoppingcartapp.domain.model.Item;
import com.shoppingcartapp.domain.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        itemRepositoryUnderTest.deleteAll();
    }

    @Test
    void shouldFindItemByItemName() {
        //given
        Item item = new Item();
        item.setItemName("Cookies");
        item.setItemCost(10F);
        item.setQuantity(2);
        itemRepositoryUnderTest.save(item);

        //when
        Optional<Item> found = itemRepositoryUnderTest.findItemByItemName(item.getItemName());

        //then
        assertEquals(item.getId(), found.get().getId());
        assertEquals(item.getItemName(), found.get().getItemName());
        assertEquals(item.getItemCost(), found.get().getItemCost());
        assertEquals(item.getQuantity(), found.get().getQuantity());
    }
}