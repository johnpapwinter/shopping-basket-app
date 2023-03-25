package com.shoppingcartapp.service;

import com.shoppingcartapp.domain.dto.ItemDTO;
import com.shoppingcartapp.domain.exceptions.ItemAlreadyExistsException;
import com.shoppingcartapp.domain.exceptions.ItemDoesNotExistException;
import com.shoppingcartapp.domain.model.Item;
import com.shoppingcartapp.domain.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ShoppingCartServiceImpl serviceUnderTest;

    @Test
    void shouldFindItemByName() {
        //given
        Item item = new Item(1L, "Milk", 1F, 2);

        when(itemRepository.findItemByItemName(item.getItemName())).thenReturn(Optional.of(item));

        //when
        Optional<Item> found = serviceUnderTest.findItemByName(item.getItemName());

        //then
        assertEquals(item.getId(), found.get().getId());
        assertEquals(item.getItemName(), found.get().getItemName());
        assertEquals(item.getItemCost(), found.get().getItemCost());
        assertEquals(item.getQuantity(), found.get().getQuantity());
    }

    @Test
    void shouldReturnNullIfItemDoesNotExist() {
        //given
        String name = "Chocolate";

        when(itemRepository.findItemByItemName(name)).thenReturn(Optional.ofNullable(null));

        //when
        Optional<Item> found = serviceUnderTest.findItemByName(name);

        //then
        assertEquals(Optional.ofNullable(null), found);
    }

    @Test
    void shouldFindAllItems() {
        //given
        Item item1 = new Item(1L, "Milk", 1F, 2);
        Item item2 = new Item(2L, "Cookies", 2F, 3);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        when(itemRepository.findAll()).thenReturn(itemList);

        //when
        List<Item> found = serviceUnderTest.findAllItems();

        //then
        assertEquals(2, found.size());
    }

    @Test
    void shouldUpdateItem() {
        //given
        Item item = new Item(1L, "Milk", 1F, 2);
        ItemDTO itemDTO = new ItemDTO("Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(item.getItemName())).thenReturn(Optional.of(item));

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);

        //when
        serviceUnderTest.updateItem(item.getItemName(), itemDTO);
        Mockito.verify(itemRepository, times(1)).save(captor.capture());
        Item actual = captor.getValue();

        //then
        assertEquals(item.getId(), actual.getId());
        assertEquals(itemDTO.getItemName(), actual.getItemName());
        assertEquals(itemDTO.getItemCost(), actual.getItemCost());
        assertEquals(itemDTO.getQuantity(), actual.getQuantity());
    }

    @Test
    void shouldThrowExceptionIfItemDoesNotExistOnUpdate() {
        //given
        Item item = new Item(1L, "Milk", 1F, 2);
        ItemDTO itemDTO = new ItemDTO("Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(item.getItemName())).thenReturn(Optional.ofNullable(null));

        //when
        //then
        assertThrows(ItemDoesNotExistException.class, () -> serviceUnderTest.updateItem(item.getItemName(), itemDTO));
    }

    @Test
    void shouldCreateItem() {
        //given
        ItemDTO itemDTO = new ItemDTO("Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(itemDTO.getItemName())).thenReturn(Optional.ofNullable(null));

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);

        //when
        serviceUnderTest.createItem(itemDTO);
        Mockito.verify(itemRepository, times(1)).save(captor.capture());
        Item actual = captor.getValue();

        //then
        assertEquals(itemDTO.getItemName(), actual.getItemName());
        assertEquals(itemDTO.getItemCost(), actual.getItemCost());
        assertEquals(itemDTO.getQuantity(), actual.getQuantity());
    }

    @Test
    void shouldThrowExceptionIfItemExistsOnCreate() {
        //given
        Item item = new Item(1L, "Chocolate Milk", 1F, 2);
        ItemDTO itemDTO = new ItemDTO("Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(itemDTO.getItemName())).thenReturn(Optional.of(item));

        //when
        //then
        assertThrows(ItemAlreadyExistsException.class, () -> serviceUnderTest.createItem(itemDTO));
    }

    @Test
    void shouldDeleteItem() {
        //given
        Item item = new Item(1L, "Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(item.getItemName())).thenReturn(Optional.of(item));

        //when
        serviceUnderTest.deleteItem(item.getItemName());

        //then
        Mockito.verify(itemRepository, times(1)).delete(Mockito.any(Item.class));
    }

    @Test
    void shouldThrowItemDoesNotExistOnDelete() {
        //given
        Item item = new Item(1L, "Chocolate Milk", 1F, 2);

        when(itemRepository.findItemByItemName(item.getItemName())).thenReturn(Optional.ofNullable(null));

        //when
        //then
        assertThrows(ItemDoesNotExistException.class, () -> serviceUnderTest.deleteItem(item.getItemName()));
    }

}