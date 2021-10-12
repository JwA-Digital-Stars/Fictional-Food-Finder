package net.digitalstars.controller;

import net.digitalstars.model.Owner;
import net.digitalstars.service.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController("ownerController")
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;
    
    //private final TruckService truckService;
    
    @Autowired
    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
        //this.truckService = truckService;
    }
    
//    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
//    public void create(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String type){
//        usersService.create(email, password, name, type);
//    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Owner owner){
        ownerService.create(owner);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Owner>> findAll(){
        return new ResponseEntity<>(this.ownerService.findAll(), HttpStatus.OK); 
    }
    
    @RequestMapping("/id")
    public String findById(@RequestParam String email){
        Owner owner = ownerService.findById(email);
        System.out.println(owner);
        return owner.toString();
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        boolean result = ownerService.login(email, password);
        
        if(result)
            return "Successful login!";
        else
            return "Invalid login";
    }
    
//    @RequestMapping("/customer/addFavorite")
//    public List<Truck> addFavorite(@RequestParam int id, @RequestParam String truckId){
//        Users user = userService.getUser(id);
//        Truck truck = truckService.getTruck(truckId);
//        userService.addFavorite((Customer) user, truck);
//        
//        return userService.getFavorites(id);
//    }
//    
//    @RequestMapping("/customer/removeFavorite")
//    public List<Truck> removeFavorite(@RequestParam int id, @RequestParam String truckId){
//        Users user = userService.getUser(id);
//        Truck truck = truckService.getTruck(truckId);
//        
//        userService.removeFavorite((Customer) user, truck);
//        
//        return userService.getFavorites(id);
//    }
//    
//    @RequestMapping("/owner/addTruck")
//    public void addTruck(@RequestParam int id, @RequestParam String truckName){
//        Owner owner = (Owner) userService.getUser(id);
//        
//        if (owner != null){
//            Truck truck = truckService.create(truckName, owner);
//            userService.addTruck(owner, truck);
//        }
//    }
    
}//UserController
