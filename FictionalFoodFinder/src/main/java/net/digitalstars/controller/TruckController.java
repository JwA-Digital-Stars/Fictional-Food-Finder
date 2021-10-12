package net.digitalstars.controller;

import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import net.digitalstars.model.TruckOwner;
import net.digitalstars.service.ItemService;
import net.digitalstars.service.TruckService;
import net.digitalstars.service.TruckOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("truckController")
@RequestMapping("/truck")
public class TruckController {
    @Autowired 
    private TruckOwnerService userService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/create")
    public String createTruck(@RequestParam String name, @RequestParam int ownerId){
        TruckOwner truckOwner = (TruckOwner) userService.getUser(ownerId);
        Truck truck = truckService.create(name, truckOwner);
        
        return truck.toString();
    }
    
    @RequestMapping("/{truckName}/addItem")
    public String addItem(@RequestParam String name, @RequestParam String itemName, @RequestParam float cost){
        Truck truck = truckService.getTruck(name);
        if (truck == null)
            return "No truck found.";
        Item item = itemService.create(itemName, cost, truck);
        
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
