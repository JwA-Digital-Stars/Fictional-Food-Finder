package com.digitalstars.controller;

import com.digitalstars.model.Item;
import com.digitalstars.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @RequestMapping("/create/item")
    public String create(@RequestParam String itemName, @RequestParam float cost, @RequestParam String truckName){
        Item item = itemService.create(itemName, cost, truckName);
        return item.toString();
    }
    
    @RequestMapping("/items")
    public void getItems(){
        
    }
    
    @RequestMapping("/{truckId}/{itemName}")
    public void getItem(@RequestParam String itemName, @RequestParam String truckId){
        
    }
    
    @RequestMapping("/delete/item")
    public void delete(@RequestParam String itemName){
        
    }
}//ItemController
