package com.example.controller;

import com.example.model.Item;
import com.example.model.Truck;
import com.example.service.ItemService;
import com.example.service.TruckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckController {
    
    private TruckService truckService;
    private ItemService itemService;
    
    @RequestMapping("/createTruck")
    public String createTruck(@RequestParam String email, @RequestParam String name){
        Truck truck = truckService.create(email, name);
        
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
