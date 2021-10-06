package com.digitalstars.controller;

import com.digitalstars.model.Item;
import com.digitalstars.model.Truck;
import com.digitalstars.service.ItemService;
import com.digitalstars.service.TruckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckController {
    
    private TruckService truckService;
    private ItemService itemService;
    
    @RequestMapping("/createTruck")
    public String createTruck(@RequestParam String name, @RequestParam int ownerId){
        Truck truck = truckService.create(name, ownerId);
        
        return truck.toString();
    }
    
    @RequestMapping("/addItem")
    public String addItem(@RequestParam String name, @RequestParam String itemName, @RequestParam float cost){
        Truck truck = truckService.getTruck(name);
        Item item = itemService.create(itemName, cost);
        
        boolean result = truckService.addItem(truck, item);
        
        if (result)
            return String.format("%s added to %s truck.", item.getName(), truck.getName());
        else
            return "Could not add item.";
    }
}//TruckController
