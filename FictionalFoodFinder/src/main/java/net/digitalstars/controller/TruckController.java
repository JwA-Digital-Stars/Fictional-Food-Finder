package net.digitalstars.controller;

import javax.websocket.server.PathParam;
import net.digitalstars.model.Item;
import net.digitalstars.model.Item.ItemId;
import net.digitalstars.model.Truck;
import net.digitalstars.model.Owner;
import net.digitalstars.service.ItemService;
import net.digitalstars.service.TruckService;
import net.digitalstars.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("truckController")
@RequestMapping("/truck")
public class TruckController {
    @Autowired 
    private OwnerService ownerService;
    
    private final TruckService truckService;
    @Autowired
    private ItemService itemService;
    
    @Autowired
    public TruckController(TruckService truckService){
        super();
        this.truckService = truckService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Truck truck){
        truckService.save(truck);
        
        return truck.toString();
    }
    
    @GetMapping(path="/{truck}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Truck findById(@PathParam("truck") String name){
        return truckService.findById(name);
    }
    
    @PostMapping(path="/{truck}/addItem", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String addItem(@PathParam("truck") String name, @RequestBody Item item){
        Truck truck = truckService.findById(name);
        if (truck == null)
            return "No truck found.";
        
        boolean result = truckService.addItem(truck, item);
        
        if (result)
            return String.format("%s added to %s truck.", item.getId().getName(), truck.getName());
        else
            return "Could not add item.";
    }
    
    @PostMapping(path="/{truck}/remove/{item}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String removeItem(@PathParam("truck") String name, @PathParam("item") String itemName){
        Truck truck = truckService.findById(name);
        if (truck == null)
            return "No truck found.";
        boolean result = truckService.removeItem(truck, itemName);
        
        if (result)
            return String.format("%s removed from %s truck.", itemName, truck.getName());
        else
            return "Could not remove item.";
    }
    
    @GetMapping(path="/{truckName}/menu", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getMenu(@RequestParam String truckName){
        Truck truck = truckService.findById(truckName);
        
        if (truck == null)
            return "Error 404: Truck not found.";
        
        String menu = truckService.menu(truck);
        return menu;
    }
}//TruckController
