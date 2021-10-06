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

    @RequestMapping("/createItem")
    public String create(@RequestParam String itemName, @RequestParam float cost){
        Item item = itemService.create(itemName, cost);
        return item.toString();
    }
    
    @RequestMapping("/getItems")
    public void getItems(@RequestParam String truckName){
        
    }
    
    
    
    @RequestMapping("/deleteItem")
    public void delete(@RequestParam String itemName){
        
    }
}//ItemController
