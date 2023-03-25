package com.shoppingcartapp.service;

import com.shoppingcartapp.domain.dto.ItemDTO;
import com.shoppingcartapp.domain.exceptions.ItemAlreadyExistsException;
import com.shoppingcartapp.domain.exceptions.ItemDoesNotExistException;
import com.shoppingcartapp.domain.model.Item;
import com.shoppingcartapp.domain.repository.ItemRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.shoppingcartapp.domain.dto.mappers.DtoToModelConverter.dtoToModel;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ItemRepository itemRepository;


    @Autowired
    public ShoppingCartServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Optional<Item> findItemByName(String itemName) {
        return itemRepository.findItemByItemName(itemName);
    }


    @Override
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

    @Override
    @Transactional
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    @Override
    public void exportToExcel() throws IOException {
        List<Item> itemList = itemRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Shopping_List");
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        Row headers = sheet.createRow(0);
        headers.createCell(0).setCellValue("NAME");
        headers.getCell(0).setCellStyle(cellStyle);
        headers.createCell(1).setCellValue("COST");
        headers.getCell(1).setCellStyle(cellStyle);
        headers.createCell(2).setCellValue("QUANTITY");
        headers.getCell(2).setCellStyle(cellStyle);


        for (int i = 0; i < itemList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(itemList.get(i).getItemName());
            row.createCell(1).setCellValue(itemList.get(i).getItemCost());
            row.createCell(2).setCellValue(itemList.get(i).getQuantity());
        }

        File outFile = new File("src/main/resources/temp/list.xlsx");
        FileOutputStream outputStream = new FileOutputStream(outFile);
        workbook.write(outputStream);
        workbook.close();

    }
}
