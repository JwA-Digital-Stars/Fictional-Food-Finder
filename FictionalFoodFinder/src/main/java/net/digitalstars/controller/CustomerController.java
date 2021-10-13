package net.digitalstars.controller;

import net.digitalstars.model.Customer;
import net.digitalstars.service.CustomerService;
import java.util.List;
import net.digitalstars.model.Truck;
import net.digitalstars.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("customerController")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    
    private final TruckService truckService;
    
    @Autowired
    public CustomerController(CustomerService customerService, TruckService truckService){
        this.customerService = customerService;
        this.truckService = truckService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Customer customer){
        customerService.create(customer);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping(path="/id", produces=MediaType.APPLICATION_JSON_VALUE)
    public String findById(@RequestParam String email){
        Customer customer = customerService.findById(email);
        System.out.println(customer);
        return customer.toString();
    }
    
    @GetMapping(path="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestParam String email, @RequestParam String password){
        boolean result = customerService.login(email, password);
        
        if(result)
            return "Successful login!";
        else
            return "Invalid login";
    }
    
    @PostMapping(path="/addFavorite", consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<Truck> addFavorite(@RequestParam String email, @RequestParam String truckName){
        Customer customer = customerService.findById(email);
        customerService.addFavorite(customer, truckName);
        
        return customer.getFavorites();
    }
    
    @PostMapping(path="/removeFavorite", consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<Truck> removeFavorite(@RequestParam String email, @RequestParam String truckName){
        Customer customer = customerService.findById(email);
        
        customerService.removeFavorite(customer, truckName);
        
        return customer.getFavorites();
    }
        
}//CustomerController
