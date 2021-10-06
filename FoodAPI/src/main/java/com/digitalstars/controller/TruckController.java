package com.digitalstars.controller;

import com.digitalstars.model.Item;
import com.digitalstars.model.Truck;
import com.digitalstars.service.ItemService;
import com.digitalstars.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckController {
    @Autowired
    private TruckService truckService;
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/create/truck")
    public String createTruck(@RequestParam String name, @RequestParam int ownerId){
        Truck truck = truckService.create(name, ownerId);
        
        return truck.toString();
    }
    
    @RequestMapping("/{truckName}/addItem")
    public String addItem(@RequestParam String name, @RequestParam String itemName, @RequestParam float cost){
        Truck truck = truckService.getTruck(name);
        Item item = itemService.create(itemName, cost);
        
        boolean result = truckService.addItem(truck, item);
        
        if (result)
            return String.format("%s added to %s truck.", item.getName(), truck.getName());
        else
            return "Could not add item.";
    }
    
    @RequestMapping("/{truckName}/menu")
    public String getMenu(@RequestParam String truckName){
        Truck truck = truckService.getTruck(truckName);
        
        if (truck == null)
            return "Error 404: Truck not found.";
        return truck.getMenu().toString();
    }
}//TruckController
