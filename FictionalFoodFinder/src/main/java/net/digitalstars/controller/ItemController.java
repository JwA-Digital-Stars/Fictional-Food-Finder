package net.digitalstars.controller;

import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import net.digitalstars.service.ItemService;
import net.digitalstars.service.TruckService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("itemController")

@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    @Autowired
    private TruckService truckService;

    @RequestMapping("/create")
    public String create(@RequestParam String itemName, @RequestParam float cost, @RequestParam String truckName){
        Truck truck = truckService.getTruck(truckName);
        Item item = itemService.create(itemName, cost, truck);
        return item.toString();
    }
    
    @RequestMapping("/all")
    public List<Item> getItems(){
        return itemService.getItems();
    }
    
    @RequestMapping("/{truckId}/{itemName}")
    public void getItem(@RequestParam String itemName, @RequestParam String truckId){
        
    }
    
    @RequestMapping("/delete/item")
    public void delete(@RequestParam String itemName){
        
    }
}//ItemController
