package com.example.service;

import com.example.model.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {
    
    @Autowired
    private ItemRepository itemRepo;
    
    public Item create(String itemName, float cost) {
        return itemRepo.save(new Item(itemName, cost));
    }
    
    public void delete(Item item){
        itemRepo.delete(item);
    }
    
    public Item updateCost(Item item, float newCost){
        item.setCost(newCost);
        itemRepo.save(item);
        return item;
    }

}//ItemService