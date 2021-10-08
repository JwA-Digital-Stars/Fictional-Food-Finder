package com.digitalstars.controller;

import com.digitalstars.model.Truck;
import com.digitalstars.model.TruckOwner;
import com.digitalstars.model.User;
import com.digitalstars.service.TruckService;
import com.digitalstars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired 
    private TruckService truckService;
    
    @RequestMapping("/create/user")
    public int create(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String type){
        User user = userService.create(email, password, name, type);
        return user.getId();
    }
    
    @RequestMapping("/user/{id}")
    public String getUser(@RequestParam int id){
        User user = userService.getUser(id);
        return user.toString();
    }
    
    @RequestMapping("/customer/{id}/addFavorite")
    public void addFavorite(@RequestParam int id, @RequestParam String truckId){
        
    }
    
    @RequestMapping("/customer/{id}/removeFavorite")
    public void removeFavorite(@RequestParam int id, @RequestParam String truckId){
        
    }
    
    @RequestMapping("/owner/{id}/addTruck")
    public void addTruck(@RequestParam int id, @RequestParam String truckName){
        TruckOwner owner = (TruckOwner) userService.getUser(id);
        
        if (owner != null){
            Truck truck = truckService.create(truckName, owner.getId());
            userService.addTruck(owner, truck);
        }
    }
    
}//UserController
