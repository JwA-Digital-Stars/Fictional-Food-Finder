package net.digitalstars.controller;

import com.digitalstars.model.Customer;
import com.digitalstars.model.Truck;
import com.digitalstars.model.TruckOwner;
import com.digitalstars.model.User;
import com.digitalstars.service.TruckService;
import com.digitalstars.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired 
    private TruckService truckService;
    
    @RequestMapping(path="/create")
    public int create(String email, String password, String name, String type){
        User user = userService.create(email, password, name, type);
        return user.getId();
    }
    
    @RequestMapping(path="/{id}")
    public String getUser(@PathVariable int id){
        User user = userService.getUser(id);
        System.out.println(user);
        return user.toString();
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam String type){
        boolean result = userService.login(email, password, type);
        
        if(result)
            return type;
        else
            return "Invalid login";
    }
    
    @RequestMapping("/customer/addFavorite")
    public List<Truck> addFavorite(@RequestParam int id, @RequestParam String truckId){
        User user = userService.getUser(id);
        Truck truck = truckService.getTruck(truckId);
        userService.addFavorite((Customer) user, truck);
        
        return userService.getFavorites(id);
    }
    
    @RequestMapping("/customer/removeFavorite")
    public List<Truck> removeFavorite(@RequestParam int id, @RequestParam String truckId){
        User user = userService.getUser(id);
        Truck truck = truckService.getTruck(truckId);
        
        userService.removeFavorite((Customer) user, truck);
        
        return userService.getFavorites(id);
    }
    
    @RequestMapping("/owner/addTruck")
    public void addTruck(@RequestParam int id, @RequestParam String truckName){
        TruckOwner owner = (TruckOwner) userService.getUser(id);
        
        if (owner != null){
            Truck truck = truckService.create(truckName, owner);
            userService.addTruck(owner, truck);
        }
    }
    
}//UserController
